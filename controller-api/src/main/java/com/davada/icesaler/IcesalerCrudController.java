package com.davada.icesaler;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.icesaler.service.port.IcesalerCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.icesaler.entity.Icesaler;
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
@Path("/v1/icesalers")
@Tag(name = "Icesaler Operations", description = "Icesaler management operations")
public class IcesalerCrudController {
    //
    private final IcesalerCrudUseCase icesalerCrudUseCase;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive =";
    }

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createIcesaler", description = "create a Icesaler")
    public Uni<Response> createIcesaler(@Valid Icesaler icesaler) {
        return Uni.createFrom()
                .item(icesalerCrudUseCase.createIcesaler(icesaler))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{icesalerUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveIcesaler", description = "retrieve a Icesaler")
    public Uni<Response> retrieveIcesaler(
            @PathParam("icesalerUuid") String icesalerUuid) {

        log.debug("retrieveIcesaler icesalerUuid : " + icesalerUuid);

        return Uni.createFrom()
                .item(icesalerCrudUseCase.retrieveIcesaler(icesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllIcesaler", description = "retrieve all Icesaler")
    public Uni<Response> retrieveAllIcesaler() {
        return Uni.createFrom()
                .item(icesalerCrudUseCase.retrieveAllIcesaler())
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{icesalerUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateIcesaler", description = "update all Icesaler")
    public Uni<Response> updateIcesaler(
            @PathParam(value = "icesalerUuid") String icesalerUuid,
            String nameValuePairsJson) {

        log.debug("updateIcesaler icesalerUuid : " + icesalerUuid);
        log.debug("updateIcesaler nameValuePairsJson : " + nameValuePairsJson);

        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(icesalerCrudUseCase.updateIcesaler(icesalerUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{icesalerUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteIcesaler", description = "delete all Icesaler")
    public Uni<Response> deleteIcesaler(
            @PathParam(value = "icesalerUuid") String icesalerUuid,
            @RestQuery String permanentYn) {

        log.debug("deleteIcesaler icesalerUuid : " + icesalerUuid + ", permanentYn : " + permanentYn);

        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(icesalerCrudUseCase.deleteIcesaler(icesalerUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }
}