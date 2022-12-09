package com.davada.product;

import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.application.product.service.port.ProductQueryUseCase;
import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.YN;
import com.davada.domain.product.condition.ProductCondition;
import com.davada.domain.product.vo.BeverageCategory;
import com.davada.domain.product.vo.BeverageContainerType;
import com.davada.domain.product.vo.ProductCategory;
import com.davada.domain.product.vo.ProductPricePolicy;

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
@Tag(name = "Product Query", description = "Product query operations")
public class ProductQueryController {
    private final ProductQueryUseCase productQueryUseCase;
    private final ParameterBeanMapper productDtoMapper;

    @GET
    @Path("/{wholesalerUuid}/products")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search Product by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ProductParameterBean parameterBean) {
        ProductCondition productCondition = productDtoMapper.toProductCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(productQueryUseCase.retrieveListByCondition(
                        productCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = productQueryUseCase.countByCondition(productCondition);
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
    static class ProductParameterBean {
        @QueryParam("offset")
        int offset = 0;
        @QueryParam("limit")
        int limit = 30;
        @QueryParam("productUuid")
        String productUuid;
        @QueryParam("productPricePolicy")
        ProductPricePolicy productPricePolicy;
        @QueryParam("beverageContainerType")
        BeverageContainerType beverageContainerType;
        @QueryParam("productCategory")
        ProductCategory productCategory;
        @QueryParam("beverageCategory")
        BeverageCategory beverageCategory;
        @QueryParam("productCode")
        String productCode;
        @QueryParam("productName")
        String productName;
        @QueryParam("productAliasName")
        String productAliasName;
        @QueryParam("businessCategory")
        BusinessCategory businessCategory;
        @QueryParam("safetyStockExists")
        YN safetyStockExists;
    }
}
