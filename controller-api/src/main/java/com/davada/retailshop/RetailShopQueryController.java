package com.davada.retailshop;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.retailshop.service.port.RetailShopQueryUseCase;
import com.davada.domain.common.vo.ShopStatus;
import com.davada.domain.retailshop.condition.RetailShopCondition;
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
@Path("/v1/query/wholesalers")
@Tag(name = "RetailShop Query", description = "RetailShop query operations")
public class RetailShopQueryController {
    private final RetailShopQueryUseCase retailshopQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/retail-shops")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search RetailShop by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        RetailShopCondition retailShopCondition = parameterBeanMapper.toRetailShopCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(retailshopQueryUseCase.retrieveListByCondition(
                        retailShopCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = retailshopQueryUseCase.countByCondition(retailShopCondition);
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
        @QueryParam("retailShopStatus")
        ShopStatus retailShopStatus;
        @QueryParam("salesPersonCode")
        String salesPersonCode;
        @QueryParam("province")
        Province province;
        @QueryParam("retailShopCode")
        String retailShopCode;
        @QueryParam("retailShopName")
        String retailShopName;
        @QueryParam("representativeName")
        String representativeName;
        @QueryParam("mobilePhoneNumber")
        String mobilePhoneNumber;
    }
}