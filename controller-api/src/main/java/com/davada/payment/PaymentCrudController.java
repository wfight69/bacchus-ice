package com.davada.payment;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.payment.service.port.PaymentCrudUseCase;
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
@Path("/v1/payment")
@Tag(name = "Payment Operations", description = "Payment operations")
public class PaymentCrudController {

    private final PaymentCrudUseCase paymentCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createPayment", description = "create a Payment")
    public Uni<Response> createPayment(@Valid PaymentCrudUseCase.CreatePaymentCommand command) {
        return Uni.createFrom()
                .item(paymentCrudUseCase.createPayment(command))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{paymentUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrievePayment", description = "retrieve a Payment")
    public Uni<Response> retrievePayment(
            @PathParam("paymentUuid") String paymentUuid) {
        return Uni.createFrom()
                .item(paymentCrudUseCase.retrievePayment(paymentUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllPayment", description = "retrieve all Payment")
    public Uni<Response> retrieveAllPayment(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(paymentCrudUseCase.retrieveAllPayment(wholesalerUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{paymentUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updatePayment", description = "update a Payment")
    public Uni<Response> updatePayment(
            @PathParam(value = "paymentUuid") String paymentUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(paymentCrudUseCase.updatePayment(paymentUuid, nameValuePairs))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{paymentUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deletePayment", description = "delete a Payment")
    public Uni<Response> deletePayment(
            @PathParam(value = "paymentUuid") String paymentUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(paymentCrudUseCase.deletePayment(paymentUuid, permanent))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    Function<Object, Response.ResponseBuilder> responseBuilderFunction = f1 ->
            f1 != null ? Response.ok(CommonResponse.success(f1))
                    : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
}
