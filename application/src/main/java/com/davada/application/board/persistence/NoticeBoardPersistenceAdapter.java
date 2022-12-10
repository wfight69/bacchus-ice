package com.davada.application.board.persistence;

import com.davada.application.board.persistence.data.NoticeBoardData;
import com.davada.application.board.persistence.repository.NoticeBoardRepository;
import com.davada.application.common.ErpRequestContext;
import com.davada.domain.board.condition.NoticeBoardCondition;
import com.davada.domain.board.entity.NoticeBoard;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class NoticeBoardPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final NoticeBoardDataMapper jpaMapper;
    private final NoticeBoardRepository noticeBoardRepository;

    public void create(final NoticeBoard order) {
        order.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        var noticeBoardData = jpaMapper.toData(order);
        noticeBoardRepository.save(noticeBoardData);
    }

    public Optional<NoticeBoard> retrieve(String orderUuid) {
        var noticeBoardData = noticeBoardRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted());
        noticeBoardData.ifPresent(NoticeBoardData::increaseViewCount);
        return noticeBoardData.map(jpaMapper::fromData);
    }

    public List<NoticeBoard> retrieveAllNoticeBoard(String wholesalerUuid) {
        return noticeBoardRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public boolean update(String orderUuid, NameValuePairs nameValuePairs) {
        return noticeBoardRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(noticeBoardData -> {
                    noticeBoardData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    noticeBoardData.updateValues(nameValuePairs, jpaMapper);
                    if (!nameValuePairs.isEmpty()) {
                        noticeBoardRepository.save(noticeBoardData);
                    }
                    return true;
                }).orElse(false);
    }

    private boolean deleteNoticeBoardData(NoticeBoardData noticeBoardData, boolean permanent) {
        if (permanent) {
            noticeBoardRepository.delete(noticeBoardData);
        } else {
            noticeBoardData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
            noticeBoardRepository.save(noticeBoardData);
        }
        return true;
    }

    public boolean deleteNoticeBoard(NoticeBoard noticeBoard, boolean permanent) {
        return noticeBoardRepository.findById(noticeBoard.getNoticeBoardUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(noticeBoardData -> deleteNoticeBoardData(noticeBoardData, permanent))
                .orElse(false);
    }

    public boolean deleteNoticeBoards(Set<String> noticeBoardUuids, boolean permanent) {
        noticeBoardUuids.forEach(noticeBoardUuid ->
                noticeBoardRepository.findById(noticeBoardUuid)
                        .filter(entity -> !entity.getAuditLog().getDeleted())
                        .map(noticeBoardData -> deleteNoticeBoardData(noticeBoardData, permanent)));
        return true;
    }

    public List<NoticeBoard> retrieveListByCondition(NoticeBoardCondition condition, int offset, int limit) {
        return noticeBoardRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromData)
                .collect(Collectors.toList());
    }

    public long countByCondition(NoticeBoardCondition condition) {
        return noticeBoardRepository.countByCondition(condition);
    }

}
