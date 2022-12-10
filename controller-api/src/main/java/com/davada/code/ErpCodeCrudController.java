package com.davada.code;

import com.davada.application.code.service.port.ErpCodeCrudUseCase;
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
@Path("/v1/codes")
@Tag(name = "ErpCode Operations", description = "ErpCode management operations")
public class ErpCodeCrudController {

    private final ErpCodeCrudUseCase erpCodeCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createErpCode", description = "create a ErpCode")
    public Uni<Response> createErpCode(@Valid ErpCodeCrudUseCase.CreateErpCodeCommand command) {
        return Uni.createFrom()
                .item(erpCodeCrudUseCase.createErpCode(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @POST
    @Path("/save-list")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "saveErpCodeList", description = "save the ErpCode List")
    public Uni<Response> saveErpCodeList(@Valid ErpCodeCrudUseCase.SaveErpCodeCommand command) {
        return Uni.createFrom()
                .item(erpCodeCrudUseCase.saveErpCode(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{codeUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveErpCode", description = "retrieve a ErpCode")
    public Uni<Response> retrieveErpCode(
            @PathParam("codeUuid") String codeUuid) {
        return Uni.createFrom()
                .item(erpCodeCrudUseCase.retrieveErpCode(codeUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllErpCode", description = "retrieve all ErpCode")
    public Uni<Response> retrieveAllErpCode(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(erpCodeCrudUseCase.retrieveAllErpCode(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{codeUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateErpCode", description = "update a ErpCode")
    public Uni<Response> updateErpCode(
            @PathParam(value = "codeUuid") String codeUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(erpCodeCrudUseCase.updateErpCode(codeUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{codeUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteErpCode", description = "delete a ErpCode")
    public Uni<Response> deleteErpCode(
            @PathParam(value = "codeUuid") String codeUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(erpCodeCrudUseCase.deleteErpCode(codeUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

}
