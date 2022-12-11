package com.davada.domain.maintenance.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.YN;
import com.davada.domain.maintenance.error.RetailMaintenanceErrorCodes;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import com.davada.domain.order.vo.CalculateStatus;

import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 소매점 유지관리내역(출고,수리,회수,오버홀)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailMaintenance extends AuditableEntity implements Refinable {
    // 유지관리 UUID
    String retailMaintenanceUuid;
    // 냉장업체 UUID
    String icesalerUuid;

    // 주류도매업체 UUID
    String wholesalerUuid;
    String wholesalerName;
    String wholesalerCeoTelephone;

    // 소매점 UUID
    String retailShopUuid;
    // 소매점 코드
    String retailShopCode;
    // 소매점 상호명
    String retailShopName;
    // 소매점 업종구분
    BusinessCategory businessCategory;

    // 등록관련-----------------------
    // 등록 일자
    String createDate;
    // 등록 시간
    String createTime;
    // 유지관리 등록내역
    String createDescription;

    // 품목명외
    String productShortName;

    // 접수관련-----------------------
    // 접수생성 일자
    String acceptDate;
    // 접수생성 시간
    String acceptTime;
    // 접수담당자 UUID
    String employeeUuid;
    // 접수담당자 코드
    String employeeCode;
    // 접수담당자 이름
    String employeeName;
    // 접수 등록내역
    String acceptDescription;

    // 유지관리(a/s)진행관련-----------------------
    // 유지관리 진행일자
    String maintenanceDate;
    // 유지관리 진행시간
    String maintenanceTime;
    // 유지관리(a/s)담당자 UUID
    String maintenanceEmployeeUuid;
    // 유지관리(a/s)담당자 코드
    String maintenanceEmployeeCode;
    // 유지관리(a/s)담당자 이름
    String maintenanceEmployeeName;
    // 유지관리 진행 등록내역
    String maintenanceDescription;

    // 유지관리(a/s)승인관련-----------------------
    // 승인생성 일자
    String approvalDate;
    // 승인생성 시간
    String approvalTime;
    // 승인담당자 UUID
    String approvalEmployeeUuid;
    // 승인담당자 코드
    String approvalEmployeeCode;
    // 승인담당자 이름
    String approvalEmployeeName;
    // 승인 등록내역
    String approvalDescription;

    // 창고코드/명
    String warehouseUuid;
    String warehouseCode;
    String warehouseName;

    // 마감여부 미마감/마감
    CalculateStatus calculateStatus;

    // 유지관리유형 출고(판매)/수리/회수/정비/기타
    MaintenanceType maintenanceType;

    // 유지관리 상태
    RetailMaintenanceStatus retailMaintenanceStatus;

    // 유지관리 체널 전화/주류도매/소매점 앱
    RetailMaintenanceChannel retailMaintenanceChannel;

    // 수량
    Integer quantity;

    // 공급가
    BigDecimal amount = BigDecimal.ZERO;
    // 부가세
    BigDecimal vat = BigDecimal.ZERO;
    // 소계
    BigDecimal subtotalAmount = BigDecimal.ZERO;
    // 합계
    BigDecimal totalAmount = BigDecimal.ZERO;

    // 유지관리 상품
    Set<RetailMaintenanceItem> orderItems = new LinkedHashSet<>();

    // 청구서(주류판매계산서) 발행/미발행
    YN invoiceIssueYn;

    // 비고
    String description;

    // 완료시처리
    public void orderComplete() {
        this.retailMaintenanceStatus = RetailMaintenanceStatus.DELIVERED;
    }

    @Override
    public void refineValues() {
        if (invoiceIssueYn == null) {
            invoiceIssueYn = YN.N;
        }
        // make productShortName
    }

    public static RetailMaintenance getInstance() {
        return new RetailMaintenance();
    }

    public void registerRequestMaintenanceStatus() {
        retailMaintenanceStatus = RetailMaintenanceStatus.RECEIVED;
        //registerMaintenanceYn = YN.N;
    }

    public void registerMaintenanceStatus() {
        switch (retailMaintenanceChannel) {
            case DIRECT:
                retailMaintenanceStatus = RetailMaintenanceStatus.ACCEPTED;
                return;
            case APP:
                registerRequestMaintenanceStatus();
                return;
            default:
                throw new ErpRuntimeException(RetailMaintenanceErrorCodes.ORDER_2000, retailMaintenanceChannel.name());
        }
    }
}