package com.davada.application.icesaler.service;


import com.davada.application.icesaler.persistence.IcesalerPersistenceAdapter;
import com.davada.application.icesaler.service.port.IcesalerCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;

import com.davada.domain.icesaler.entity.Icesaler;
import com.davada.domain.icesaler.error.IcesalerErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class IcesalerCrudService implements IcesalerCrudUseCase {
    private final IcesalerPersistenceAdapter icesalerCrudPort;
    //private final IcesalerDomainMapper icesalerDomainMapper;

    @Override
    public IdValue createIcesaler(Icesaler icesaler) {
        String icesalerUuid = ErpId.newId().getUuid().toString();
        icesaler.setIcesalerUuid(icesalerUuid);
        //
        icesalerCrudPort.create(icesaler);
        return new IdValue("icesalerUuid", icesalerUuid);
    }

    @Override
    public Icesaler retrieveIcesaler(String icesalerUuid) {
        return icesalerCrudPort.retrieve(icesalerUuid)
                .orElseThrow(() -> new ErpRuntimeException(IcesalerErrorCodes.ICESALER_1000, icesalerUuid));
    }

    @Override
    public List<Icesaler> retrieveAllIcesaler() {
        return icesalerCrudPort.retrieveAllIcesaler();
    }

    @Override
    public BooleanValue updateIcesaler(String icesalerUuid, NameValuePairs nameValuePairs) {
        return icesalerCrudPort.retrieve(icesalerUuid).map(workplace -> {
            boolean modified = icesalerCrudPort.update(icesalerUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new WorkplaceRemovedEvent(workplace, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(icesalerUuid));
    }

    @Override
    public BooleanValue deleteIcesaler(String icesalerUuid, boolean permanent) {
        return icesalerCrudPort.retrieve(icesalerUuid).map(workplace -> {
            boolean removed = icesalerCrudPort.delete(workplace, permanent);
            if (removed) {
                // domainEventPublisher.publish(new WorkplaceRemovedEvent(workplace, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(icesalerUuid));
    }

}
