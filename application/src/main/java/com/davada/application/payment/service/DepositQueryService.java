package com.davada.application.payment.service;

import com.davada.application.payment.persistence.DepositPersistenceAdapter;
import com.davada.application.payment.service.port.DepositQueryUseCase;
import com.davada.domain.payment.condition.DepositCondition;
import com.davada.dto.payment.DepositDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class DepositQueryService implements DepositQueryUseCase {
    private final DepositPersistenceAdapter depositQueryPort;
    private final DepositMapper depositDtoMapper;

    @Override
    public List<DepositDto> retrieveListByCondition(DepositCondition condition, int offset, int limit) {
        return depositQueryPort.retrieveListByCondition(condition, offset, limit)
                .stream()
                .map(depositDtoMapper::toDepositDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countByCondition(DepositCondition condition) {
        return depositQueryPort.countByCondition(condition);
    }
}
