package com.davada.domain.maintenance.entity;

import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.common.vo.YN;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
import com.davada.domain.maintenance.vo.RetailMaintenanceStatus;
import com.davada.domain.order.vo.CalculateStatus;
import com.davada.domain.order.vo.RetailOrderChannel;
import com.davada.domain.order.vo.RetailOrderStatus;
import com.davada.domain.order.vo.SalesType;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.davada.domain.order.error.RetailOrderErrorCodes.ORDER_2000;

/**
 * 소매점 관리내역(출고,수리,회수,오버홀)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetailMaintenance extends AuditableEntity implements Refinable {
    // 주문 UUID
    String orderUuid;
    // 냉장업체 UUID
    String icesalerUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    String wholesalerName;
    // 소매점 UUID
    String retailShopUuid;
    // 소매점 코드
    String retailShopCode;
    // 소매점 상호명
    String retailShopName;
    // 소매점 업종구분
    BusinessCategory businessCategory;
    // 판매담당자 UUID
    String employeeUuid;
    // 판매담당자 이름
    String employeeName;
    // 판매담당자 코드
    String employeeCode;
    // 판매담당자 코스
    CodeName salesCourse;
    // 창고
    String warehouseUuid;
    String warehouseCode;
    // 창고명
    String warehouseName;
    // 청구서(주류판매계산서) 발행/미발행
    YN invoiceIssueYn;
    // 부가세 적용여부
    YN vatYn;
    // 마감여부 미마감/마감
    CalculateStatus calculateStatus;
    // 매출유형 판매/반품/기증/파손/차량출고
    SalesType salesType;

    // 유지관리유형 출고(판매)/수리/회수/정비/기타
    MaintenanceType maintenanceType;

    // 유지관리 상태
    RetailMaintenanceStatus retailMaintenanceStatus;

    // 유지관리 체널 전화/주류도매/소매점 앱
    RetailMaintenanceChannel retailMaintenanceChannel;

    // 주문요청 일자
    String orderDate;
    // 주문요청 시간
    String orderTime;
    // 전표생성 일자
    String orderCreateDate;
    // 전표생성 시간
    String orderCreateTime;
    // 품목명외
    String productShortName;
    // 박스
    Integer containerQuantity;
    // 본
    Integer bottleQuantity;

    // 공급가
    BigDecimal amount = BigDecimal.ZERO;
    // 부가세
    BigDecimal vat = BigDecimal.ZERO;
    // 소계
    BigDecimal subtotalAmount = BigDecimal.ZERO;
    // 용기보증금
    BigDecimal containerDeposit = BigDecimal.ZERO;
    // 공병보증금
    BigDecimal bottleDeposit = BigDecimal.ZERO;
    // 합계
    BigDecimal totalAmount = BigDecimal.ZERO;

    // 주문상태
    RetailOrderStatus retailOrderStatus;
    // 비고
    String description;
    // 주문상품
    Set<RetailMaintenanceItem> orderItems = new LinkedHashSet<>();
    //---------
    // 주문 채널
    RetailOrderChannel retailOrderChannel;
    // 읽기 여부(청취 여부)
    YN readYn;
    // 전표생성 여부
    YN registerOrderYn;
    // 주문요청 전화번호
    String retailOrderTelephone;
    // 주문내역
    String orderDescription;
    // 음성파일 아이디
    String voiceFileId;

    public void orderComplete() {
        this.retailOrderStatus = RetailOrderStatus.DELIVERED;
    }

    @Override
    public void refineValues() {
        if (invoiceIssueYn == null) {
            invoiceIssueYn = YN.N;
        }
        if (vatYn == null) {
            vatYn = YN.N;
        }
        if (readYn == null) {
            readYn = YN.N;
        }
        if (registerOrderYn == null) {
            registerOrderYn = YN.N;
        }

        // make productShortName
    }

    public static RetailMaintenance getInstance() {
        return new RetailMaintenance();
    }

    public void registerRequestOrderStatus() {
        retailOrderStatus = RetailOrderStatus.RECEIVED;
        readYn = YN.N;
        registerOrderYn = YN.N;
    }

    public void registerOrderStatus() {
        switch (retailOrderChannel) {
            case DIRECT:
                retailOrderStatus = RetailOrderStatus.ACCEPTED;
                readYn = YN.Y;
                registerOrderYn = YN.Y;
                return;
            case APP:
                registerRequestOrderStatus();
                return;
            default:
                throw new ErpRuntimeException(ORDER_2000, retailOrderChannel.name());
        }
    }
}