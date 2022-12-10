package com.davada.application.board.persistence.repository;

import com.davada.application.board.persistence.data.NoticeBoardData;
import com.davada.application.common.CriteriaQueryHelper;
import com.davada.domain.board.condition.NoticeBoardCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class NoticeBoardRepositoryCustomImpl implements NoticeBoardRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<NoticeBoardData> findListByCondition(NoticeBoardCondition condition, int offset, int limit) {
        CriteriaQueryHelper<NoticeBoardData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, NoticeBoardData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list("auditLog", "createTimestamp", CriteriaQueryHelper.Order.DESC, offset, limit);
    }

    @Override
    public long countByCondition(NoticeBoardCondition condition) {
        CriteriaQueryHelper<NoticeBoardData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, NoticeBoardData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<NoticeBoardData> criteriaBuilder, NoticeBoardCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addLike("employeeName", condition.getEmployeeName());
        criteriaBuilder.addLike("mainText", condition.getMainText());
//        criteriaBuilder.greaterThan("views", condition.getViews());
        criteriaBuilder.addEqual("views", condition.getViews());
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
