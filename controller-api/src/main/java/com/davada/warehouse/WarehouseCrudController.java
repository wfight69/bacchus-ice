package com.davada.warehouse;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.warehouse.service.port.WarehouseCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.product.entity.Warehouse;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/warehouses")
@Tag(name = "Warehouse Operations", description = "Warehouse management operations")
public class WarehouseCrudController {

    private final WarehouseCrudUseCase warehouseCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createWarehouse", description = "create a Warehouse")
    public Uni<Response> createWarehouse(@Valid Warehouse warehouse) {
        return Uni.createFrom()
                .item(warehouseCrudUseCase.createWarehouse(warehouse))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{warehouseUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveWarehouse", description = "retrieve a Warehouse")
    public Uni<Response> retrieveWarehouse(
            @PathParam("warehouseUuid") String warehouseUuid) {
        return Uni.createFrom()
                .item(warehouseCrudUseCase.retrieveWarehouse(warehouseUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllWarehouse", description = "retrieve all Warehouse")
    public Uni<Response> retrieveAllWarehouse(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(warehouseCrudUseCase.retrieveAllWarehouse(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{warehouseUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateWarehouse", description = "update a Warehouse")
    public Uni<Response> updateWarehouse(
            @PathParam(value = "warehouseUuid") String warehouseUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(warehouseCrudUseCase.updateWarehouse(warehouseUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{warehouseUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteWarehouse", description = "delete a Warehouse")
    public Uni<Response> deleteWarehouse(
            @PathParam(value = "warehouseUuid") String warehouseUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(warehouseCrudUseCase.deleteWarehouse(warehouseUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

}
