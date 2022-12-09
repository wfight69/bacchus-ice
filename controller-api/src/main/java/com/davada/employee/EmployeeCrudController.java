package com.davada.employee;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.employee.service.port.EmployeeCrudUseCase;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.wholesaler.entity.Employee;
import com.davada.domain.wholesaler.entity.ErpUser;
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
@Path("/v1/employees")
@Tag(name = "Employee Operations", description = "Employee management operations")
public class EmployeeCrudController {

    private final EmployeeCrudUseCase employeeCrudUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createEmployee", description = "create a Employee")
    public Uni<Response> createEmployee(@Valid Employee employee) {
        return Uni.createFrom()
                .item(employeeCrudUseCase.createEmployee(employee))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{employeeUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveEmployee", description = "retrieve a Employee")
    public Uni<Response> retrieveEmployee(
            @PathParam("employeeUuid") String employeeUuid) {

        log.debug("============================ retrieveEmployee.");
        return Uni.createFrom()
                .item(employeeCrudUseCase.retrieveEmployee(employeeUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/dto/{employeeUuid}")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveEmployee", description = "retrieve a Employee")
    public Uni<Response> retrieveEmployeeDto(
            @PathParam("employeeUuid") String employeeUuid) {

        log.debug("============================ retrieveEmployee.");
        return Uni.createFrom()
                .item(employeeCrudUseCase.retrieveEmployeeDto(employeeUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllEmployee", description = "retrieve all Employee")
    public Uni<Response> retrieveAllEmployee(
            @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(employeeCrudUseCase.retrieveAllEmployee(wholesalerUuid))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{employeeUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateEmployee", description = "update a Employee")
    public Uni<Response> updateEmployee(
            @PathParam(value = "employeeUuid") String employeeUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(employeeCrudUseCase.updateEmployee(employeeUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{employeeUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteEmployee", description = "delete a Employee")
    public Uni<Response> deleteEmployee(
            @PathParam(value = "employeeUuid") String employeeUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(employeeCrudUseCase.deleteEmployee(employeeUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    @POST
    @Path("/{employeeUuid}/user")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createErpUser", description = "create a ErpUser")
    public Uni<Response> createErpUser(
            @PathParam(value = "employeeUuid") String employeeUuid,
            @Valid ErpUser erpUser) {
        return Uni.createFrom()
                .item(employeeCrudUseCase.createErpUser(employeeUuid, erpUser))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{employeeUuid}/user/{erpUserUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateErpUser", description = "update a ErpUser")
    public Uni<Response> updateErpUser(
            @PathParam(value = "employeeUuid") String employeeUuid,
            @PathParam(value = "erpUserUuid") String erpUserUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(employeeCrudUseCase.updateErpUser(employeeUuid, erpUserUuid, nameValuePairs))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{employeeUuid}/user/{erpUserUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteErpUser", description = "delete a ErpUser")
    public Uni<Response> deleteErpUser(
            @PathParam(value = "employeeUuid") String employeeUuid,
            @PathParam(value = "erpUserUuid") String erpUserUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(employeeCrudUseCase.deleteErpUser(employeeUuid, erpUserUuid, permanent))
                .onItem()
                .transform(f -> f != null ? Response.ok(CommonResponse.success(f)) : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }
}
