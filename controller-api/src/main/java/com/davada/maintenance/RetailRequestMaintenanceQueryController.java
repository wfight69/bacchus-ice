package com.davada.maintenance;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.maintenance.service.port.RetailRequestMaintenanceQueryUseCase;
import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;
import com.davada.domain.maintenance.vo.MaintenanceType;
import com.davada.domain.maintenance.vo.RetailMaintenanceChannel;
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
import java.util.function.Function;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/query/wholesalers")
@Tag(name = "RetailRequestMaintenance Query", description = "RetailRequestMaintenance query operations")
public class RetailRequestMaintenanceQueryController {
    private final RetailRequestMaintenanceQueryUseCase retailRequestMaintenanceQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;


    @GET
    @Path("/{wholesalerUuid}/request-maintenances")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search RequestMaintenance by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        RetailRequestMaintenanceCondition orderCondition = parameterBeanMapper.toCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(retailRequestMaintenanceQueryUseCase.retrieveListByCondition(
                        orderCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = retailRequestMaintenanceQueryUseCase.countByCondition(orderCondition);
                        ok.header("X-ERP-TOTAL-COUNT", count);
                        return ok;
                    } else {
                        return Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
                    }
                })
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    Function<Object, Response.ResponseBuilder> responseBuilderFunction = f1 ->
            f1 != null ? Response.ok(CommonResponse.success(f1))
                    : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));

    @Data
    static class ParameterBean {
        @QueryParam("offset")
        int offset = 0;
        @QueryParam("limit")
        int limit = 30;
        @QueryParam("maintenanceType")
        MaintenanceType maintenanceType;
        @QueryParam("retailMaintenanceChannel")
        RetailMaintenanceChannel retailMaintenanceChannel;
//        @QueryParam("retailRequestMaintenanceStatus")
//        RetailRequestMaintenanceStatus retailRequestMaintenanceStatus;
        @QueryParam("startMaintenanceDate")
        String startMaintenanceDate;
        @QueryParam("endMaintenanceDate")
        String endMaintenanceDate;
        @QueryParam("salesCourseCode")
        String salesCourseCode;
        @QueryParam("salesManagerName")
        String salesManagerName;
        @QueryParam("retailShopCode")
        String retailShopCode;
        @QueryParam("retailShopName")
        String retailShopName;
    }
}
