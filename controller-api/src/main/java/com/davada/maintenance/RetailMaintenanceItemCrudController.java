package com.davada.maintenance;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.maintenance.service.port.RetailMaintenanceItemCrudUseCase;
import com.davada.domain.common.NameValuePairs;
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
@Path("/v1/maintenance-items")
@Tag(name = "MaintenanceItem Operations", description = "MaintenanceItem operations")
public class RetailMaintenanceItemCrudController {

    private final RetailMaintenanceItemCrudUseCase retailMaintenanceItemCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createMaintenanceItem", description = "create a MaintenanceItem")
    public Uni<Response> createMaintenanceItem(@Valid RetailMaintenanceItemCrudUseCase.CreateMaintenanceItemCommand command) {
        return Uni.createFrom()
                .item(retailMaintenanceItemCrudUseCase.createMaintenanceItem(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{maintenanceItemUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveMaintenanceItem", description = "retrieve a MaintenanceItem")
    public Uni<Response> retrieveMaintenanceItem(
            @PathParam("maintenanceItemUuid") String maintenanceItemUuid) {
        return Uni.createFrom()
                .item(retailMaintenanceItemCrudUseCase.retrieveMaintenanceItem(maintenanceItemUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{maintenanceItemUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateMaintenanceItem", description = "update a MaintenanceItem")
    public Uni<Response> updateMaintenanceItem(
            @PathParam(value = "maintenanceItemUuid") String maintenanceItemUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(retailMaintenanceItemCrudUseCase.updateMaintenanceItem(maintenanceItemUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{maintenanceItemUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteMaintenanceItem", description = "delete a MaintenanceItem")
    public Uni<Response> deleteMaintenanceItem(
            @PathParam(value = "maintenanceItemUuid") String maintenanceItemUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(retailMaintenanceItemCrudUseCase.deleteMaintenanceItem(maintenanceItemUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }
}
