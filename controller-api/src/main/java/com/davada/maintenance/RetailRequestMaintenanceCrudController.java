package com.davada.maintenance;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.maintenance.service.port.RetailRequestMaintenanceCrudUseCase;
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
@Path("/v1/request-maintenances")
@Tag(name = "RetailRequestMaintenance Operations", description = "RetailRequestMaintenance operations")
public class RetailRequestMaintenanceCrudController {

    private final RetailRequestMaintenanceCrudUseCase retailRequestMaintenanceCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createRequestMaintenance", description = "create a RequestMaintenance")
    public Uni<Response> createRequestMaintenance(@Valid RetailRequestMaintenanceCrudUseCase.CreateRequestMaintenanceCommand command) {
        return Uni.createFrom()
                .item(retailRequestMaintenanceCrudUseCase.createRequestMaintenance(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{requestMaintenanceUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteRequestMaintenance", description = "delete a RequestMaintenance")
    public Uni<Response> deleteRequestMaintenance(
            @PathParam(value = "requestMaintenanceUuid") String requestMaintenanceUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(retailRequestMaintenanceCrudUseCase.deleteRequestMaintenance(requestMaintenanceUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    @POST
    @Path("/deleted-records")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteRequestMaintenances", description = "delete the RequestMaintenance list")
    public Uni<Response> deleteRequestMaintenances(@Valid RetailRequestMaintenanceCrudUseCase.DeleteRequestMaintenanceCommand command) {
        return Uni.createFrom()
                .item(retailRequestMaintenanceCrudUseCase.deleteRequestMaintenances(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

}
