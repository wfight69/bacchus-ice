package com.davada.application.board.service;

import com.davada.application.board.persistence.NoticeBoardPersistenceAdapter;
import com.davada.application.board.service.port.NoticeBoardCrudUseCase;
import com.davada.domain.board.entity.NoticeBoard;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpEntityNotFoundException;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.util.DateUtil;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.common.vo.IdValue;
import com.davada.dto.board.NoticeBoardDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.board.error.NoticeBoardErrorCodes.NOTICE_BOARD_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class NoticeBoardCrudService implements NoticeBoardCrudUseCase {

    public final NoticeBoardPersistenceAdapter noticeBoardCrudPort;
    private final NoticeBoardMapper noticeBoardMapper;

    @Override
    public IdValue createNoticeBoard(CreateNoticeBoardCommand command) {
        String noticeBoardUuid = ErpId.newId().getUuid().toString();
        Integer views = 0;
        String issueDate = DateUtil.getCurrentDateAsString(DateUtil.DATE_FORMAT);
        NoticeBoard noticeBoard = noticeBoardMapper.toDomain(noticeBoardUuid, views, issueDate, command);
        noticeBoard.refineValues();
        noticeBoardCrudPort.create(noticeBoard);
        return new IdValue("noticeBoardUuid", noticeBoardUuid);
    }

    @Override
    public NoticeBoardDto retrieveNoticeBoard(String noticeBoardUuid) {
        return noticeBoardCrudPort.retrieve(noticeBoardUuid)
                .map(noticeBoardMapper::toNoticeBoardDto)
                .orElseThrow(() -> new ErpRuntimeException(NOTICE_BOARD_1000, noticeBoardUuid));
    }

    @Override
    public List<NoticeBoardDto> retrieveAllNoticeBoard(String wholesalerUuid) {
        return noticeBoardCrudPort.retrieveAllNoticeBoard(wholesalerUuid)
                .stream().map(noticeBoardMapper::toNoticeBoardDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooleanValue updateNoticeBoard(String noticeBoardUuid, NameValuePairs nameValuePairs) {
        return noticeBoardCrudPort.retrieve(noticeBoardUuid).map(order -> {
            boolean modified = noticeBoardCrudPort.update(noticeBoardUuid, nameValuePairs);
            if (modified) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("modified", modified);
        }).orElseThrow(() -> new ErpEntityNotFoundException(noticeBoardUuid));
    }

    @Override
    public BooleanValue deleteNoticeBoard(String noticeBoardUuid, boolean permanent) {
        return noticeBoardCrudPort.retrieve(noticeBoardUuid).map(order -> {
            boolean removed = noticeBoardCrudPort.deleteNoticeBoard(order, permanent);
            if (removed) {
                // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
            }
            return new BooleanValue("removed", removed);
        }).orElseThrow(() -> new ErpEntityNotFoundException(noticeBoardUuid));
    }

    @Override
    public BooleanValue deleteNoticeBoards(DeleteNoticeBoardCommand command) {
        boolean removed = noticeBoardCrudPort.deleteNoticeBoards(command.getNoticeBoardUuids(), command.isPermanent());
        if (removed) {
            // domainEventPublisher.publish(new OrderRemovedEvent(order, permanent));
        }
        return new BooleanValue("removed", removed);
    }
}
