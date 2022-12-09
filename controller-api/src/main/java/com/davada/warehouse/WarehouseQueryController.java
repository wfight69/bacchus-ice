package com.davada.warehouse;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.warehouse.service.port.WarehouseQueryUseCase;
import com.davada.domain.product.condition.WarehouseCondition;

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
@Tag(name = "Warehouse Query", description = "Warehouse query operations")
public class WarehouseQueryController {
    private final WarehouseQueryUseCase warehouseQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/warehouses")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search Warehouse by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        WarehouseCondition condition = parameterBeanMapper.toWarehouseCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(warehouseQueryUseCase.retrieveListByCondition(condition, parameterBean.getOffset(), parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = warehouseQueryUseCase.countByCondition(condition);
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
        @QueryParam("warehouseUuid")
        String warehouseUuid;
        @QueryParam("warehouseCode")
        String warehouseCode;
        @QueryParam("warehouseName")
        String warehouseName;
    }
}