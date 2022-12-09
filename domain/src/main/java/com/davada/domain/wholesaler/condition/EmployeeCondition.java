package com.davada.domain.wholesaler.condition;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeCondition {
    String wholesalerUuid;
    String employeeName;
    String employeeCode;
    boolean resign;

    public EmployeeCondition(String wholesalerUuid,
                             String employeeName,
                             String employeeCode,
                             String resignYn
    ) {
        if (!isEmpty(wholesalerUuid)) {
            this.wholesalerUuid = wholesalerUuid;
        }
        if (!isEmpty(employeeName)) {
            this.employeeName = employeeName;
        }
        if (!isEmpty(employeeCode)) {
            this.employeeCode = employeeCode;
        }
        if (!isEmpty(resignYn)) {
            this.resign = "Y".equals(resignYn);
        }
    }
}
