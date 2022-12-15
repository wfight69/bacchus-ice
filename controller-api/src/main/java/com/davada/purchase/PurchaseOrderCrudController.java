package com.davada.purchase;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.purchase.service.port.PurchaseOrderCrudUseCase;
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
@Path("/v1/purchase-orders")
@Tag(name = "PurchaseOrder Operations", description = "PurchaseOrder operations")
public class PurchaseOrderCrudController {

    private final PurchaseOrderCrudUseCase purchaseOrderCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createPurchaseOrder", description = "create a PurchaseOrder")
    public Uni<Response> createPurchaseOrder(@Valid PurchaseOrderCrudUseCase.CreatePurchaseOrderCommand command) {
        return Uni.createFrom()
                .item(purchaseOrderCrudUseCase.createPurchaseOrder(command))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{purchaseOrderUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrievePurchaseOrder", description = "retrieve a PurchaseOrder")
    public Uni<Response> retrievePurchaseOrder(
            @PathParam("purchaseOrderUuid") String purchaseOrderUuid) {
        return Uni.createFrom()
                .item(purchaseOrderCrudUseCase.retrievePurchaseOrder(purchaseOrderUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllPurchaseOrder", description = "retrieve all PurchaseOrder")
    public Uni<Response> retrieveAllPurchaseOrder(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(purchaseOrderCrudUseCase.retrieveAllPurchaseOrder(wholesalerUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{purchaseOrderUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updatePurchaseOrder", description = "update a PurchaseOrder")
    public Uni<Response> updatePurchaseOrder(
            @PathParam(value = "purchaseOrderUuid") String purchaseOrderUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(purchaseOrderCrudUseCase.updatePurchaseOrder(purchaseOrderUuid, nameValuePairs))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{purchaseOrderUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deletePurchaseOrder", description = "delete a PurchaseOrder")
    public Uni<Response> deletePurchaseOrder(
            @PathParam(value = "purchaseOrderUuid") String purchaseOrderUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(purchaseOrderCrudUseCase.deletePurchaseOrder(purchaseOrderUuid, permanent))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    Function<Object, Response.ResponseBuilder> responseBuilderFunction = f1 ->
            f1 != null ? Response.ok(CommonResponse.success(f1))
                    : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
}
