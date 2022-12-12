package com.davada.maintenance;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.maintenance.service.port.RetailMaintenanceCrudUseCase;
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
import java.util.function.Function;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/maintenances")
@Tag(name = "Maintenance Operations", description = "Maintenance operations")
public class RetailMaintenanceCrudController {

    private final RetailMaintenanceCrudUseCase retailMaintenanceCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createMaintenance", description = "create a Maintenance")
    public Uni<Response> createMaintenance(@Valid RetailMaintenanceCrudUseCase.CreateMaintenanceCommand command) {
        return Uni.createFrom()
                .item(retailMaintenanceCrudUseCase.createMaintenance(command))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{maintenanceUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveMaintenance", description = "retrieve a Maintenance")
    public Uni<Response> retrieveMaintenance(
            @PathParam("maintenanceUuid") String maintenanceUuid) {
        return Uni.createFrom()
                .item(retailMaintenanceCrudUseCase.retrieveMaintenance(maintenanceUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllMaintenance", description = "retrieve all Maintenance")
    public Uni<Response> retrieveAllMaintenance(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(retailMaintenanceCrudUseCase.retrieveAllMaintenance(wholesalerUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{maintenanceUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateMaintenance", description = "update a Maintenance")
    public Uni<Response> updateMaintenance(
            @PathParam(value = "maintenanceUuid") String maintenanceUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(retailMaintenanceCrudUseCase.updateMaintenance(maintenanceUuid, nameValuePairs))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{maintenanceUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteMaintenance", description = "delete a Maintenance")
    public Uni<Response> deleteMaintenance(
            @PathParam(value = "maintenanceUuid") String maintenanceUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(retailMaintenanceCrudUseCase.deleteMaintenance(maintenanceUuid, permanent))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    Function<Object, Response.ResponseBuilder> responseBuilderFunction = f1 ->
            f1 != null ? Response.ok(CommonResponse.success(f1))
                    : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
}
