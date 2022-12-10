package com.davada.application.code.service;

import com.davada.application.code.persistence.ErpCodeSetPersistenceAdapter;
import com.davada.application.code.service.port.ErpCodeSetCrudUseCase;
import com.davada.domain.code.entity.ErpCodeSet;
import com.davada.domain.code.error.ErpCodeSetErrorCodes;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.dto.code.ErpCodeSetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ErpCodeSetCrudService implements ErpCodeSetCrudUseCase {

    private final ErpCodeSetPersistenceAdapter erpCodeSetCrudPort;
    private final ErpCodeSetDomainMapper erpCodeSetDomainMapper;
    private final ErpCodeSetDtoMapper erpCodeSetDtoMapper;

    @Override
    public IdValue createErpCodeSet(CreateErpCodeSetCommand command) {
        String codeSetUuid = ErpId.newId().getUuid().toString();
        ErpCodeSet erpCodeSet = erpCodeSetDomainMapper.toDomain(codeSetUuid, command);
        erpCodeSet.refineValues();

        erpCodeSetCrudPort.create(erpCodeSet);
        return new IdValue("codeSetUuid", codeSetUuid);
    }

    @Override
    public ErpCodeSetDto retrieveErpCodeSet(String codeSetUuid) {
        ErpCodeSet erpCodeSet = erpCodeSetCrudPort.retrieve(codeSetUuid)
                .orElseThrow(() -> new ErpRuntimeException(ErpCodeSetErrorCodes.CODE_SET_0002, codeSetUuid));

        return erpCodeSetDtoMapper.toErpCodeSetDto(erpCodeSet);
    }

    @Override
    public List<ErpCodeSetDto> retrieveAllErpCodeSet(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return erpCodeSetCrudPort.retrieveAllErpCodeSet(wholesalerUuid)
                .stream()
                .map(erpCodeSet -> {

                    return erpCodeSetDtoMapper.toErpCodeSetDto(erpCodeSet);
                })
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateErpCodeSet(String codeSetUuid, NameValuePairs nameValuePairs) {
        return erpCodeSetCrudPort.retrieve(codeSetUuid).map(erpCodeSet -> {
            boolean modified = erpCodeSetCrudPort.update(codeSetUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new ErpCodeSetRemovedEvent(erpCodeSet, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(codeSetUuid));
    }

    @Override
    public BooleanValue deleteErpCodeSet(String codeSetUuid, boolean permanent) {
        return erpCodeSetCrudPort.retrieve(codeSetUuid).map(erpCodeSet -> {
            boolean removed = erpCodeSetCrudPort.delete(erpCodeSet, permanent);
            if (removed) {
                // domainEventPublisher.publish(new ErpCodeSetRemovedEvent(erpCodeSet, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(codeSetUuid));
    }
}
