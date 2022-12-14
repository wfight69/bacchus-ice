package com.davada.payment;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.payment.service.port.DepositCrudUseCase;
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
@Path("/v1/deposit")
@Tag(name = "Deposit Operations", description = "Deposit operations")
public class DepositCrudController {

    private final DepositCrudUseCase depositCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createDeposit", description = "create a Deposit")
    public Uni<Response> createDeposit(@Valid DepositCrudUseCase.CreateDepositCommand command) {
        return Uni.createFrom()
                .item(depositCrudUseCase.createDeposit(command))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{depositUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveDeposit", description = "retrieve a Deposit")
    public Uni<Response> retrieveDeposit(
            @PathParam("depositUuid") String depositUuid) {
        return Uni.createFrom()
                .item(depositCrudUseCase.retrieveDeposit(depositUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllDeposit", description = "retrieve all Deposit")
    public Uni<Response> retrieveAllDeposit(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(depositCrudUseCase.retrieveAllDeposit(wholesalerUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{depositUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateDeposit", description = "update a Deposit")
    public Uni<Response> updateDeposit(
            @PathParam(value = "depositUuid") String depositUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(depositCrudUseCase.updateDeposit(depositUuid, nameValuePairs))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{depositUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteDeposit", description = "delete a Deposit")
    public Uni<Response> deleteDeposit(
            @PathParam(value = "depositUuid") String depositUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(depositCrudUseCase.deleteDeposit(depositUuid, permanent))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    Function<Object, Response.ResponseBuilder> responseBuilderFunction = f1 ->
            f1 != null ? Response.ok(CommonResponse.success(f1))
                    : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
}
