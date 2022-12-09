package com.davada.retailshop;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.retailshop.service.port.RetailOrderTelephoneUseCase;
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
@Path("/v1/retail-order-telephones")
@Tag(name = "RetailOrderTelephone Operations", description = "RetailOrderTelephone operations")
public class RetailOrderTelephoneController {

    private final RetailOrderTelephoneUseCase retailOrderTelephoneUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createRetailOrderTelephone", description = "create a RetailOrderTelephone")
    public Uni<Response> createRetailOrderTelephone(@Valid RetailOrderTelephoneUseCase.CreateRetailOrderTelephoneCommand command) {
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.createRetailOrderTelephone(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{retailOrderTelephoneUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveRetailOrderTelephone", description = "retrieve a RetailOrderTelephone")
    public Uni<Response> retrieveRetailOrderTelephone(
            @PathParam("retailOrderTelephoneUuid") String retailOrderTelephoneUuid) {
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.retrieveRetailOrderTelephone(retailOrderTelephoneUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllRetailOrderTelephone", description = "retrieve all RetailOrderTelephone")
    public Uni<Response> retrieveAllRetailOrderTelephone(
            @QueryParam("retailShopUuid") String retailShopUuid) {
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.retrieveAllRetailOrderTelephone(retailShopUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{retailOrderTelephoneUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateRetailOrderTelephone", description = "update all RetailOrderTelephone")
    public Uni<Response> updateRetailOrderTelephone(
            @PathParam(value = "retailOrderTelephoneUuid") String retailOrderTelephoneUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.updateRetailOrderTelephone(retailOrderTelephoneUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{retailOrderTelephoneUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteRetailOrderTelephone", description = "delete all RetailOrderTelephone")
    public Uni<Response> deleteRetailOrderTelephone(
            @PathParam(value = "retailOrderTelephoneUuid") String retailOrderTelephoneUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.deleteRetailOrderTelephone(retailOrderTelephoneUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    @PUT
    @Path("/merge/{retailShopUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "mergeRetailOrderTelephone", description = "merge all RetailOrderTelephone")
    public Uni<Response> mergeRetailOrderTelephone(
            @PathParam(value = "retailShopUuid") String retailShopUuid,
            RetailOrderTelephoneUseCase.MergeRetailOrderTelephoneCommand mergeRetailOrderTelephoneCommand) {
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.mergeRetailOrderTelephone(retailShopUuid, mergeRetailOrderTelephoneCommand))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

}
