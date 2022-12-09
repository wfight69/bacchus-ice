package com.davada.application.retailshop.service;

import com.davada.application.retailshop.persistence.RetailOrderTelephonePersistenceAdapter;
import com.davada.application.retailshop.persistence.repository.RetailOrderTelephoneRepository;
import com.davada.application.retailshop.service.port.RetailOrderTelephoneUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;
import com.davada.domain.retailshop.error.RetailOrderTelephoneErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailOrderTelephoneService implements RetailOrderTelephoneUseCase {

    public final RetailOrderTelephonePersistenceAdapter retailOrderTelephoneCrudPort;
    private final RetailOrderTelephoneDomainMapper retailOrderTelephoneDomainMapper;
    private final RetailShopDtoMapper retailShopDtoMapper;

    @Override
    public IdValue createRetailOrderTelephone(RetailOrderTelephoneUseCase.CreateRetailOrderTelephoneCommand command) {
        String retailOrderTelephoneUuid = ErpId.newId().getUuid().toString();
        RetailOrderTelephone retailOrderTelephone = retailOrderTelephoneDomainMapper.toDomain(retailOrderTelephoneUuid, command);
        retailOrderTelephone.refineValues();
        retailOrderTelephoneCrudPort.create(retailOrderTelephone);
        return new IdValue("retailOrderTelephoneUuid", retailOrderTelephoneUuid);
    }

    @Override
    public RetailOrderTelephone retrieveRetailOrderTelephone(String retailOrderTelephoneUuid) {
        return retailOrderTelephoneCrudPort.retrieve(retailOrderTelephoneUuid).orElseThrow(() -> new ErpRuntimeException(RetailOrderTelephoneErrorCodes.RETAILORDERTELEPHONE_1000, retailOrderTelephoneUuid));
    }

    @Override
    public List<RetailOrderTelephone> retrieveAllRetailOrderTelephone(String wholesalerUuid) {
        if (isEmpty(wholesalerUuid)) {
            throw new ErpEntityNotFoundException(wholesalerUuid);
        }
        return retailOrderTelephoneCrudPort.retrieveAllRetailOrderTelephone(wholesalerUuid);
    }

    @Override
    public BooleanValue updateRetailOrderTelephone(String retailOrderTelephoneUuid, NameValuePairs nameValuePairs) {
        return retailOrderTelephoneCrudPort.retrieve(retailOrderTelephoneUuid).map(retailOrderTelephone -> {
            boolean modified = retailOrderTelephoneCrudPort.update(retailOrderTelephoneUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new RetailOrderTelephoneRemovedEvent(retailOrderTelephone, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(retailOrderTelephoneUuid));
    }

    @Override
    public BooleanValue deleteRetailOrderTelephone(String retailOrderTelephoneUuid, boolean permanent) {
        return retailOrderTelephoneCrudPort.retrieve(retailOrderTelephoneUuid).map(retailOrderTelephone -> {
            boolean removed = retailOrderTelephoneCrudPort.delete(retailOrderTelephone, permanent);
            if (removed) {
                // domainEventPublisher.publish(new RetailOrderTelephoneRemovedEvent(retailOrderTelephone, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(retailOrderTelephoneUuid));
    }

    @Override
    public BooleanValue mergeRetailOrderTelephone(String retailShopUuid, MergeRetailOrderTelephoneCommand mergeRetailOrderTelephoneCommand) {
        List<RetailOrderTelephone> retailOrderTelephones = mergeRetailOrderTelephoneCommand.getRetailOrderTelephones().stream()
                .map(retailShopDtoMapper::toRetailOrderTelephone)
                .collect(Collectors.toList());

        retailOrderTelephoneCrudPort.merge(retailShopUuid, retailOrderTelephones);
        return new BooleanValue("merged", true);
    }

}
