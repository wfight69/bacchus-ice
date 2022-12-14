package com.davada.payment;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.payment.service.port.DepositQueryUseCase;
import com.davada.domain.payment.condition.DepositCondition;
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
@Tag(name = "Deposit Query", description = "Deposit query operations")
public class DepositQueryController {
    private final DepositQueryUseCase depositQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/deposit")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search Deposit by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        DepositCondition depositCondition = parameterBeanMapper.toCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(depositQueryUseCase.retrieveListByCondition(
                        depositCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = depositQueryUseCase.countByCondition(depositCondition);
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
        @QueryParam("retailShopUuid")
        String retailShopUuid;
        @QueryParam("retailShopCode")
        String retailShopCode;
        @QueryParam("retailShopName")
        String retailShopName;
        @QueryParam("employeeCode")
        String employeeCode;
        @QueryParam("employeeName")
        String employeeName;
        // 전표일자
        @QueryParam("startCreateDate")
        String startCreateDate;
        @QueryParam("endCreateDate")
        String endCreateDate;
        // 입금일자
        @QueryParam("startPaymentDate")
        String startPaymentDate;
        @QueryParam("endPaymentDate")
        String endPaymentDate;
    }

}
