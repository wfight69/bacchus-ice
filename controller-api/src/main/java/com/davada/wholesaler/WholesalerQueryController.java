package com.davada.wholesaler;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.wholesaler.service.port.WholesalerQueryUseCase;
import com.davada.domain.common.vo.IndustryType;
import com.davada.domain.wholesaler.condition.WholesalerCondition;
import com.davada.domain.wholesaler.vo.Province;
import com.davada.wholesaler.service.WholesalerQueryDtoService;
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
@Tag(name = "Wholesaler Query", description = "Wholesaler query operations")
public class WholesalerQueryController {
    private final WholesalerQueryUseCase wholesalerQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search Wholesaler by condition")
    public Uni<Response> retrieveListByCondition(@BeanParam ParameterBean parameterBean
    ) {
        WholesalerCondition wholesalerCondition = parameterBeanMapper.toWholesalerCondition(parameterBean);
        return Uni.createFrom()
                .item(wholesalerQueryUseCase.retrieveListByCondition(wholesalerCondition, parameterBean.getOffset(), parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = wholesalerQueryUseCase.countByCondition(wholesalerCondition);
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
        @QueryParam("wholesalerCode")
        String wholesalerCode;
        @QueryParam("industryType")
        IndustryType industryType;
        @QueryParam("province")
        Province province;
        @QueryParam("wholesalerName")
        String wholesalerName;
        @QueryParam("businessNumber")
        String businessNumber;
    }
}
