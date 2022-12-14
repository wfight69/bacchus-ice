package com.davada.application.payment.persistence;

import com.davada.application.common.persistence.data.CodeNameData;
import com.davada.application.payment.persistence.data.DepositData;

import com.davada.domain.common.vo.CodeName;
import com.davada.domain.payment.entity.Deposit;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DepositDataMapper {
    @Mapping(target = "version", ignore = true)
    DepositData toData(Deposit deposit);

    Deposit fromData(DepositData depositData);

    CodeNameData toCodeNameData(CodeName codeName);
}