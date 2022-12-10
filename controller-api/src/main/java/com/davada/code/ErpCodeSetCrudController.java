package com.davada.code;

import com.davada.application.code.service.port.ErpCodeSetCrudUseCase;
import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
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
@Path("/v1/code-sets")
@Tag(name = "ErpCodeSet Operations", description = "ErpCodeSet management operations")
public class ErpCodeSetCrudController {

    private final ErpCodeSetCrudUseCase erpCodeSetCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createErpCodeSet", description = "create a ErpCodeSet")
    public Uni<Response> createErpCodeSet(@Valid ErpCodeSetCrudUseCase.CreateErpCodeSetCommand command) {
        return Uni.createFrom()
                .item(erpCodeSetCrudUseCase.createErpCodeSet(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{codeSetUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveErpCodeSet", description = "retrieve a ErpCodeSet")
    public Uni<Response> retrieveErpCodeSet(
            @PathParam("codeSetUuid") String codeSetUuid) {
        return Uni.createFrom()
                .item(erpCodeSetCrudUseCase.retrieveErpCodeSet(codeSetUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllErpCodeSet", description = "retrieve all ErpCodeSet")
    public Uni<Response> retrieveAllErpCodeSet(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(erpCodeSetCrudUseCase.retrieveAllErpCodeSet(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{codeSetUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateErpCodeSet", description = "update a ErpCodeSet")
    public Uni<Response> updateErpCodeSet(
            @PathParam(value = "codeSetUuid") String codeSetUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(erpCodeSetCrudUseCase.updateErpCodeSet(codeSetUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{codeSetUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteErpCodeSet", description = "delete a ErpCodeSet")
    public Uni<Response> deleteErpCodeSet(
            @PathParam(value = "codeSetUuid") String codeSetUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(erpCodeSetCrudUseCase.deleteErpCodeSet(codeSetUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

}
