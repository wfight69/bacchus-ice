package com.davada.code;

import com.davada.application.code.service.port.ErpCodeSetQueryUseCase;
import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.domain.code.condition.ErpCodeSetCondition;
import com.davada.domain.code.vo.ErpCodeSetType;

import io.smallrye.mutiny.Uni;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/query/wholesalers")
@Tag(name = "ErpCodeSet Query", description = "ErpCodeSet query operations")
public class ErpCodeSetQueryController {
    private final ErpCodeSetQueryUseCase erpCodeSetQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/code-sets")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search ErpCodeSet by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        ErpCodeSetCondition condition = parameterBeanMapper.toErpCodeSetCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(erpCodeSetQueryUseCase.retrieveListByCondition(condition, parameterBean.getOffset(), parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = erpCodeSetQueryUseCase.countByCondition(condition);
                        ok.header("X-ERP-TOTAL-COUNT", count);
                        return ok;
                    } else {
                        return Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
                    }
                })
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @Data
    static class ParameterBean {
        @QueryParam("offset")
        int offset = 0;
        @QueryParam("limit")
        int limit = 30;
        @QueryParam("wholesalerUuid")
        private String wholesalerUuid;
        @QueryParam("name")
        private String name;
        @QueryParam("nameKeyword")
        private String nameKeyword;
        @QueryParam("label")
        private String label;
        @QueryParam("erpCodeSetType")
        private ErpCodeSetType erpCodeSetType;
    }
}