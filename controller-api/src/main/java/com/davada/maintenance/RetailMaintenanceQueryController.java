package com.davada.maintenance;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.maintenance.service.port.RetailMaintenanceQueryUseCase;
import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;
import com.davada.domain.maintenance.vo.MaintenanceType;

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
@Tag(name = "Maintenance Query", description = "Maintenance query operations")
public class RetailMaintenanceQueryController {
    private final RetailMaintenanceQueryUseCase retailMaintenanceQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/orders")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search Maintenance by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        RetailMaintenanceCondition retailMaintenanceCondition = parameterBeanMapper.toCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(retailMaintenanceQueryUseCase.retrieveListByCondition(
                        retailMaintenanceCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = retailMaintenanceQueryUseCase.countByCondition(retailMaintenanceCondition);
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
        @QueryParam("maintenanceType")
        MaintenanceType maintenanceType;
        @QueryParam("employeeUuid")
        String employeeUuid;
        @QueryParam("startMaintenanceDate")
        String startMaintenanceDate;
        @QueryParam("endMaintenanceDate")
        String endMaintenanceDate;
        @QueryParam("startMaintenanceCreateDate")
        String startMaintenanceCreateDate;
        @QueryParam("endMaintenanceCreateDate")
        String endMaintenanceCreateDate;
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
