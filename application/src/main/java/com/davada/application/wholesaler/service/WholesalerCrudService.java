package com.davada.application.wholesaler.service;


import com.davada.application.wholesaler.persistence.WholesalerPersistenceAdapter;
import com.davada.application.wholesaler.service.port.WholesalerCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.wholesaler.entity.Wholesaler;
import com.davada.domain.wholesaler.error.WholesalerErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class WholesalerCrudService implements WholesalerCrudUseCase {
    private final WholesalerPersistenceAdapter wholesalerCrudPort;
    private final WholesalerDomainMapper wholesalerDomainMapper;

    @Override
    public IdValue createWholesaler(Wholesaler wholesaler) {
        String wholesalerUuid = ErpId.newId().getUuid().toString();
        // wholesalerDomainMapper.toDomain(wholesalerUuid, command)
        wholesaler.setWholesalerUuid(wholesalerUuid);
        //
        wholesalerCrudPort.create(wholesaler);
        return new IdValue("wholesalerUuid", wholesalerUuid);
    }

    @Override
    public Wholesaler retrieveWholesaler(String wholesalerUuid) {
        return wholesalerCrudPort.retrieve(wholesalerUuid)
                .orElseThrow(() -> new ErpRuntimeException(WholesalerErrorCodes.WHOLESALER_1000, wholesalerUuid));
    }

    @Override
    public List<Wholesaler> retrieveAllWholesaler() {
        return wholesalerCrudPort.retrieveAllWholesaler();
    }

    @Override
    public BooleanValue updateWholesaler(String wholesalerUuid, NameValuePairs nameValuePairs) {
        return wholesalerCrudPort.retrieve(wholesalerUuid).map(workplace -> {
            boolean modified = wholesalerCrudPort.update(wholesalerUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new WorkplaceRemovedEvent(workplace, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(wholesalerUuid));
    }

    @Override
    public BooleanValue deleteWholesaler(String wholesalerUuid, boolean permanent) {
        return wholesalerCrudPort.retrieve(wholesalerUuid).map(workplace -> {
            boolean removed = wholesalerCrudPort.delete(workplace, permanent);
            if (removed) {
                // domainEventPublisher.publish(new WorkplaceRemovedEvent(workplace, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(wholesalerUuid));
    }

}
