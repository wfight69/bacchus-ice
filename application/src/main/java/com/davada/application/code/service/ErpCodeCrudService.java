package com.davada.application.code.service;

import com.davada.application.code.persistence.ErpCodePersistenceAdapter;
import com.davada.application.code.service.port.ErpCodeCrudUseCase;
import com.davada.domain.code.entity.ErpCode;
import com.davada.domain.code.error.ErpCodeErrorCodes;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.dto.code.ErpCodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeCrudService implements ErpCodeCrudUseCase {

    private final ErpCodePersistenceAdapter erpCodeCrudPort;
    private final ErpCodeDomainMapper erpCodeDomainMapper;
    private final ErpCodeDtoMapper erpCodeDtoMapper;

    @Override
    public IdValue createErpCode(CreateErpCodeCommand command) {
        String codeUuid = ErpId.newId().getUuid().toString();
        ErpCode erpCode = erpCodeDomainMapper.toDomain(codeUuid, command);
        erpCode.refineValues();

        erpCodeCrudPort.create(erpCode);
        return new IdValue("codeUuid", codeUuid);
    }

    @Override
    public BooleanValue saveErpCode(SaveErpCodeCommand command) {
        List<ErpCode> erpCodes = command.getCodes().stream().map(createErpCodeCommand -> {
            ErpCode erpCode = erpCodeDomainMapper.toDomain(createErpCodeCommand);
            erpCode.refineValues();
            return erpCode;
        }).toList();
        erpCodeCrudPort.save(erpCodes);
        return new BooleanValue("saved", true);
    }

    @Override
    public ErpCodeDto retrieveErpCode(String codeUuid) {
        ErpCode erpCode = erpCodeCrudPort.retrieve(codeUuid)
                .orElseThrow(() -> new ErpRuntimeException(ErpCodeErrorCodes.CODE_0002, codeUuid));

        return erpCodeDtoMapper.toErpCodeDto(erpCode);
    }

    @Override
    public List<ErpCodeDto> retrieveAllErpCode(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return erpCodeCrudPort.retrieveAllErpCode(wholesalerUuid)
                .stream()
                .map(erpCodeDtoMapper::toErpCodeDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateErpCode(String codeUuid, NameValuePairs nameValuePairs) {
        return erpCodeCrudPort.retrieve(codeUuid).map(erpCode -> {
            boolean modified = erpCodeCrudPort.update(codeUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new ErpCodeRemovedEvent(erpCode, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(codeUuid));
    }

    @Override
    public BooleanValue deleteErpCode(String codeUuid, boolean permanent) {
        return erpCodeCrudPort.retrieve(codeUuid).map(erpCode -> {
            boolean removed = erpCodeCrudPort.delete(erpCode, permanent);
            if (removed) {
                // domainEventPublisher.publish(new ErpCodeRemovedEvent(erpCode, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(codeUuid));
    }
}
