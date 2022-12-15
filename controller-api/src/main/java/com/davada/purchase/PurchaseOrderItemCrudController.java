package com.davada.purchase;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.purchase.service.port.PurchaseOrderItemCrudUseCase;
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
@Path("/v1/purchase-order-items")
@Tag(name = "PurchaseOrderItem Operations", description = "PurchaseOrderItem operations")
public class PurchaseOrderItemCrudController {

    private final PurchaseOrderItemCrudUseCase purchaseOrderItemCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createPurchaseOrderItem", description = "create a PurchaseOrderItem")
    public Uni<Response> createPurchaseOrderItem(@Valid PurchaseOrderItemCrudUseCase.CreatePurchaseOrderItemCommand command) {
        return Uni.createFrom()
                .item(purchaseOrderItemCrudUseCase.createPurchaseOrderItem(command))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{purchaseOrderItemUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrievePurchaseOrderItem", description = "retrieve a PurchaseOrderItem")
    public Uni<Response> retrievePurchaseOrderItem(
            @PathParam("purchaseOrderItemUuid") String purchaseOrderItemUuid) {
        return Uni.createFrom()
                .item(purchaseOrderItemCrudUseCase.retrievePurchaseOrderItem(purchaseOrderItemUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{purchaseOrderItemUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updatePurchaseOrderItem", description = "update a PurchaseOrderItem")
    public Uni<Response> updatePurchaseOrderItem(
            @PathParam(value = "purchaseOrderItemUuid") String purchaseOrderItemUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(purchaseOrderItemCrudUseCase.updatePurchaseOrderItem(purchaseOrderItemUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{purchaseOrderItemUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deletePurchaseOrderItem", description = "delete a PurchaseOrderItem")
    public Uni<Response> deletePurchaseOrderItem(
            @PathParam(value = "purchaseOrderItemUuid") String purchaseOrderItemUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(purchaseOrderItemCrudUseCase.deletePurchaseOrderItem(purchaseOrderItemUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f))
                        : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }
}
