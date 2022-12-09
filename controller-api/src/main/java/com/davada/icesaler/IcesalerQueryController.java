package com.davada.icesaler;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.icesaler.service.port.IcesalerQueryUseCase;
import com.davada.domain.common.vo.IndustryType;
import com.davada.domain.icesaler.condition.IcesalerCondition;
import com.davada.domain.wholesaler.vo.Province;
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
@Path("/v1/query/icesalers")
@Tag(name = "Icesaler Query", description = "Icesaler query operations")
public class IcesalerQueryController {
    private final IcesalerQueryUseCase icesalerQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search Icesaler by condition")
    public Uni<Response> retrieveListByCondition(@BeanParam ParameterBean parameterBean
    ) {
        IcesalerCondition icesalerCondition = parameterBeanMapper.toIcesalerCondition(parameterBean);
        return Uni.createFrom()
                .item(icesalerQueryUseCase.retrieveListByCondition(icesalerCondition, parameterBean.getOffset(), parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = icesalerQueryUseCase.countByCondition(icesalerCondition);
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
        @QueryParam("icesalerCode")
        String icesalerCode;
        @QueryParam("industryType")
        IndustryType industryType;
        @QueryParam("province")
        Province province;
        @QueryParam("icesalerName")
        String icesalerName;
        @QueryParam("businessNumber")
        String businessNumber;
    }
}
