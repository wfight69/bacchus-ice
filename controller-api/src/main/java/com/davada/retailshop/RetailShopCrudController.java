package com.davada.retailshop;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.retailshop.service.port.RetailOrderTelephoneUseCase;
import com.davada.application.retailshop.service.port.RetailShopCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.retailshop.entity.RetailShop;
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
@Path("/v1/retail-shops")
@Tag(name = "RetailShop Operations", description = "RetailShop operations")
public class RetailShopCrudController {

    private final RetailShopCrudUseCase retailShopCrudUseCase;
    private final RetailOrderTelephoneUseCase retailOrderTelephoneUseCase;

//    @POST
//    @Transactional(value = Transactional.TxType.REQUIRED)
//    @Operation(operationId = "createRetailShop", description = "create a RetailShop")
//    public Uni<Response> createRetailShop(@Valid RetailShopCrudUseCase.CreateRetailShopCommand command) {
//        return Uni.createFrom()
//                .item(retailShopCrudUseCase.createRetailShop(command))
//                .onItem()
//                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
//                .onItem()
//                .transform(Response.ResponseBuilder::build);
//    }

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createRetailShop", description = "create a RetailShop")
    public Uni<Response> createRetailShop(@Valid RetailShop retailShop) {
        return Uni.createFrom()
                .item(retailShopCrudUseCase.createRetailShop(retailShop))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{retailShopUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveRetailShop", description = "retrieve a RetailShop")
    public Uni<Response> retrieveRetailShop(
            @PathParam("retailShopUuid") String retailShopUuid) {
        return Uni.createFrom()
                .item(retailShopCrudUseCase.retrieveRetailShop(retailShopUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllRetailShop", description = "retrieve all RetailShop")
    public Uni<Response> retrieveAllRetailShop(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(retailShopCrudUseCase.retrieveAllRetailShop(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{retailShopUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateRetailShop", description = "update all RetailShop")
    public Uni<Response> updateRetailShop(
            @PathParam(value = "retailShopUuid") String retailShopUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(retailShopCrudUseCase.updateRetailShop(retailShopUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{retailShopUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteRetailShop", description = "delete all RetailShop")
    public Uni<Response> deleteRetailShop(
            @PathParam(value = "retailShopUuid") String retailShopUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(retailShopCrudUseCase.deleteRetailShop(retailShopUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    @GET
    @Path("/{retailShopUuid}/retailordertelephones")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllRetailOrderTelephone", description = "retrieve all RetailOrderTelephone")
    public Uni<Response> retrieveAllRetailOrderTelephone(
            @PathParam("retailShopUuid") String retailShopUuid) {
        return Uni.createFrom()
                .item(retailOrderTelephoneUseCase.retrieveAllRetailOrderTelephone(retailShopUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }
}
