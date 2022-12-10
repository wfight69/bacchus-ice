package com.davada.board;

import com.davada.application.board.service.port.NoticeBoardCrudUseCase;
import com.davada.application.board.service.port.NoticeBoardQueryUseCase;
import com.davada.application.common.CommonResponse;
import com.davada.application.common.ErrorCode;
import com.davada.domain.board.condition.NoticeBoardCondition;
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
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/notice-boards")
@Tag(name = "NoticeBoard Operations", description = "NoticeBoard operations")
public class NoticeBoardCrudController {

    private final NoticeBoardCrudUseCase noticeBoardCrudUseCase;
    private final NoticeBoardQueryUseCase noticeBoardQueryUseCase;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "createNoticeBoard", description = "create a NoticeBoard")
    public Uni<Response> createNoticeBoard(@Valid NoticeBoardCrudUseCase.CreateNoticeBoardCommand command) {
        return Uni.createFrom()
                .item(noticeBoardCrudUseCase.createNoticeBoard(command))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    //TODO: 공지사항 팝업용 API : 최종 이면서 읽지 않은것만 리턴한다. // 사용자에 따라, 사용자체크는 토큰으로...
    @GET
    @Path("/{noticeBoardUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED) // Need Tx for retrieve for update view count
    @Operation(operationId = "retrieveNoticeBoard", description = "retrieve a NoticeBoard")
    public Uni<Response> retrieveNoticeBoard(
            @PathParam("noticeBoardUuid") String noticeBoardUuid) {
        return Uni.createFrom()
                .item(noticeBoardCrudUseCase.retrieveNoticeBoard(noticeBoardUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/unread")
    @Transactional(value = Transactional.TxType.REQUIRED) // Need Tx for retrieve for update view count
    @Operation(operationId = "retrieveUnreadNoticeBoard", description = "retrieve a unread NoticeBoard")
    public Uni<Response> retrieveUnreadNoticeBoard(
            @NotBlank @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(noticeBoardQueryUseCase.retrieveListByCondition(new NoticeBoardCondition(wholesalerUuid, null, null, 0), 0, 1))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @GET
    @Transactional(value = Transactional.TxType.SUPPORTS)
    @Operation(operationId = "retrieveAllNoticeBoard", description = "retrieve all NoticeBoard")
    public Uni<Response> retrieveAllNoticeBoard(
            @NotBlank @QueryParam("wholesalerUuid") String wholesalerUuid) {
        return Uni.createFrom()
                .item(noticeBoardCrudUseCase.retrieveAllNoticeBoard(wholesalerUuid))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{noticeBoardUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "updateNoticeBoard", description = "update a NoticeBoard")
    public Uni<Response> updateNoticeBoard(
            @PathParam(value = "noticeBoardUuid") String noticeBoardUuid,
            String nameValuePairsJson) {
        NameValuePairs nameValuePairs = NameValuePairs.convertFromJson(nameValuePairsJson);
        return Uni.createFrom()
                .item(noticeBoardCrudUseCase.updateNoticeBoard(noticeBoardUuid, nameValuePairs))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{noticeBoardUuid}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Operation(operationId = "deleteNoticeBoard", description = "delete a NoticeBoard")
    public Uni<Response> deleteNoticeBoard(
            @PathParam(value = "noticeBoardUuid") String noticeBoardUuid,
            @RestQuery String permanentYn) {
        boolean permanent = permanentYn == null ? Boolean.FALSE : "Y".equalsIgnoreCase(permanentYn);
        return Uni.createFrom()
                .item(noticeBoardCrudUseCase.deleteNoticeBoard(noticeBoardUuid, permanent))
                .onItem()
                .transform(f -> responseBuilderFunction.apply(f))
                .onItem()
                .transform(Response.ResponseBuilder::build);

    }

    Function<Object, Response.ResponseBuilder> responseBuilderFunction = f1 ->
            f1 != null ? Response.ok(CommonResponse.success(f1))
                    : Response.ok(CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
}
