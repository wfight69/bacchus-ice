package com.davada.application.payment.service;


import com.davada.application.payment.service.port.DepositCrudUseCase;
import com.davada.domain.payment.entity.Deposit;
import com.davada.dto.payment.DepositDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DepositMapper {
    @Mapping(target = "auditLog", ignore = true)
    @Mapping(target = "closeYn", expression = "java(YN.N)")
    Deposit toDomain(String depositUuid, DepositCrudUseCase.CreateDepositCommand command);

    DepositDto toDepositDto(Deposit deposit);
}
