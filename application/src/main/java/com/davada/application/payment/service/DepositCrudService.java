package com.davada.application.payment.service;

import com.davada.application.payment.persistence.DepositPersistenceAdapter;
import com.davada.application.payment.service.port.DepositCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;

import com.davada.domain.payment.entity.Deposit;
import com.davada.dto.payment.DepositDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.payment.error.OrderPaymentErrorCodes.ORDER_PAYMENT_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class DepositCrudService implements DepositCrudUseCase {

    public final DepositPersistenceAdapter depositCrudPort;
    private final DepositMapper depositMapper;

    @Override
    public IdValue createDeposit(CreateDepositCommand command) {
        String depositUuid = ErpId.newId().getUuid().toString();
        Deposit deposit = depositMapper.toDomain(depositUuid, command);
        deposit.refineValues();
        depositCrudPort.create(deposit);
        return new IdValue("depositUuid", depositUuid);
    }

    @Override
    public DepositDto retrieveDeposit(String depositUuid) {
        return depositCrudPort.retrieve(depositUuid)
                .map(depositMapper::toDepositDto)
                .orElseThrow(() -> new ErpRuntimeException(ORDER_PAYMENT_1000, depositUuid));
    }

    @Override
    public List<DepositDto> retrieveAllDeposit(String wholesalerUuid) {
        return depositCrudPort.retrieveAllDeposit(wholesalerUuid)
                .stream().map(depositMapper::toDepositDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateDeposit(String depositUuid, NameValuePairs nameValuePairs) {
        return depositCrudPort.retrieve(depositUuid).map(order -> {
            boolean modified = depositCrudPort.update(depositUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(depositUuid));
    }

    @Override
    public BooleanValue deleteDeposit(String depositUuid, boolean permanent) {
        return depositCrudPort.retrieve(depositUuid).map(order -> {
            boolean removed = depositCrudPort.deleteDeposit(order, permanent);
            if (removed) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(depositUuid));
    }

    @Override
    public BooleanValue deleteDeposits(DeleteDepositCommand command) {
        boolean removed = depositCrudPort.deleteDeposits(command.getDepositUuids(), command.isPermanent());
        if (removed) {
            // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
        }
        return new BooleanValue("removed", removed);
    }
}
