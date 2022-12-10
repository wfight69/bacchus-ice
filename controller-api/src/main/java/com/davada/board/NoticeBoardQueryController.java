package com.davada.board;

import com.davada.application.board.service.port.NoticeBoardQueryUseCase;
import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.domain.board.condition.NoticeBoardCondition;
import com.davada.domain.order.vo.PurchaseType;

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
@Tag(name = "NoticeBoard Query", description = "NoticeBoard query operations")
public class NoticeBoardQueryController {
    private final NoticeBoardQueryUseCase noticeBoardQueryUseCase;
    private final ParameterBeanMapper parameterBeanMapper;

    @GET
    @Path("/{wholesalerUuid}/notice-boards")
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveListByCondition", description = "search NoticeBoard by condition")
    public Uni<Response> retrieveListByCondition(
            @PathParam("wholesalerUuid") String wholesalerUuid,
            @BeanParam ParameterBean parameterBean) {
        NoticeBoardCondition noticeBoardCondition = parameterBeanMapper.toCondition(wholesalerUuid, parameterBean);
        return Uni.createFrom()
                .item(noticeBoardQueryUseCase.retrieveListByCondition(
                        noticeBoardCondition,
                        parameterBean.getOffset(),
                        parameterBean.getLimit()))
                .onItem()
                .transform(f -> {
                    if (f != null) {
                        Response.ResponseBuilder ok = Response.ok(CommonResponse.success(f));
                        long count = noticeBoardQueryUseCase.countByCondition(noticeBoardCondition);
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
        @QueryParam("purchaseType")
        PurchaseType purchaseType;
        @QueryParam("employeeName")
        String employeeName;
        @QueryParam("mainText")
        String mainText;
    }

}
