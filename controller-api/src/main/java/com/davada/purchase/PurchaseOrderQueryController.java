package com.davada.purchase;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.purchase.service.port.PurchaseOrderQueryUseCase;
import com.davada.domain.purchase.condition.PurchaseOrderCondition;
import com.davada.domain.purchase.vo.PurchaseType;
import io.smallrye.mutiny.Uni;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/query/wholesalers")
@Tag(name = "PurchaseOrder Query", description = "PurchaseOrder query operations")
public class PurchaseOrderQueryController {
    private final PurchaseOrderQueryUseCase purchaseOrderQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/purchase-orders")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search PurchaseOrder by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        PurchaseOrderCondition purchaseOrderCondition = parameterBeanMapper.toCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(purchaseOrderQueryUseCase.retrieveListByCondition(
                        purchaseOrderCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = purchaseOrderQueryUseCase.countByCondition(purchaseOrderCondition);
                        ok.header("X-ERP-TOTAL-COUNT", count);
                        return ok;
                    } else {
                        return Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
                    }
                })
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @Data
    static class ParameterBean {
        @QueryParam("offset")
        int offset = 0;
        @QueryParam("limit")
        int limit = 30;
        @QueryParam("purchaseType")
        PurchaseType purchaseType;
        @QueryParam("startOrderDate")
        String startOrderDate;
        @QueryParam("endOrderDate")
        String endOrderDate;
        @QueryParam("supplierCode")
        String supplierCode;
        @QueryParam("supplierName")
        String supplierName;
    }

}
