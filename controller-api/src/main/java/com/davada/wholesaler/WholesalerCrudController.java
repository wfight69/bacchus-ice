package com.davada.wholesaler;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.wholesaler.service.port.WholesalerCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.wholesaler.entity.Wholesaler;
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
@Path("/v1/wholesalers")
@Tag(name = "Wholesaler Operations", description = "Wholesaler management operations")
public class WholesalerCrudController {
    //
    private final WholesalerCrudUseCase wholesalerCrudUseCase;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive =";
    }

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createWholesaler", description = "create a Wholesaler")
    public Uni<Response> createWholesaler(@Valid Wholesaler wholesaler) {
        return Uni.createFrom()
                .item(wholesalerCrudUseCase.createWholesaler(wholesaler))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{wholesalerUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveWholesaler", description = "retrieve a Wholesaler")
    public Uni<Response> retrieveWholesaler(
            @PathParam("wholesalerUuid") String wholesalerUuid) {

        log.debug("retrieveWholesaler wholesalerUuid : " + wholesalerUuid);

        return Uni.createFrom()
                .item(wholesalerCrudUseCase.retrieveWholesaler(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllWholesaler", description = "retrieve all Wholesaler")
    public Uni<Response> retrieveAllWholesaler() {
        return Uni.createFrom()
                .item(wholesalerCrudUseCase.retrieveAllWholesaler())
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{wholesalerUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateWholesaler", description = "update all Wholesaler")
    public Uni<Response> updateWholesaler(
            @PathParam(value = "wholesalerUuid") String wholesalerUuid,
            String nameValuePairsJson) {

        log.debug("updateWholesaler wholesalerUuid : " + wholesalerUuid);
        log.debug("updateWholesaler nameValuePairsJson : " + nameValuePairsJson);

        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(wholesalerCrudUseCase.updateWholesaler(wholesalerUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{wholesalerUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteWholesaler", description = "delete all Wholesaler")
    public Uni<Response> deleteWholesaler(
            @PathParam(value = "wholesalerUuid") String wholesalerUuid,
            @RestQuery String permanentYn) {

        log.debug("deleteWholesaler wholesalerUuid : " + wholesalerUuid + ", permanentYn : " + permanentYn);

        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(wholesalerCrudUseCase.deleteWholesaler(wholesalerUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }
}