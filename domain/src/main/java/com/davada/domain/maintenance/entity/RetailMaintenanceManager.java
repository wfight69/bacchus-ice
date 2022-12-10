package com.davada.domain.maintenance.entity;


import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 소매점의 유지관리 관리자(담당자)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailMaintenanceManager extends AuditableEntity implements Refinable {
    //소매점 유지관리 관리자 UUID
    String retailMaintenanceManagerUuid;
    // 유지관리 담당자 UUID
    String employeeUuid;
    // 유지관리 담당자 이름
    String employeeName;
    // 유지관리 담당자 전화번호
    String telephone;
    // 유지관리 일자
    String maintenanceDate;
    // 유지관리 시간
    String maintenanceTime;
    //비고
    String description;

    // 유지관리 상태
    RetailMaintenanceStatus retailMaintenanceStatus;

    @Override
    public void refineValues() {

    }
}
