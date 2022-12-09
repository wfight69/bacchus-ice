package com.davada.product;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.product.service.port.ProductCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.product.entity.Product;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestQuery;

import javax.annotation.security.RolesAllowed;
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
@Path("/v1/products")
@Tag(name = "Product Operations", description = "Product Product")
//@RolesAllowed(value = {"admin", "user"})
public class ProductCrudController {

    private final ProductCrudUseCase productCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createProduct", description = "create a Product")
    public Uni<Response> createProduct(@Valid Product product) {
        return Uni.createFrom()
                .item(productCrudUseCase.createProduct(product))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{productUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveProduct", description = "retrieve a Product")
    public Uni<Response> retrieveProduct(
            @PathParam("productUuid") String productUuid) {
        return Uni.createFrom()
                .item(productCrudUseCase.retrieveProduct(productUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllProduct", description = "retrieve all Product")
    public Uni<Response> retrieveAllProduct(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(productCrudUseCase.retrieveAllProduct(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{productUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateProduct", description = "update a Product")
    public Uni<Response> updateProduct(
            @PathParam(value = "productUuid") String productUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(productCrudUseCase.updateProduct(productUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @RolesAllowed(value = {"admin"})
    @Path("/{productUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteProduct", description = "delete a Product")
    public Uni<Response> deleteProduct(
            @PathParam(value = "productUuid") String productUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(productCrudUseCase.deleteProduct(productUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

}
