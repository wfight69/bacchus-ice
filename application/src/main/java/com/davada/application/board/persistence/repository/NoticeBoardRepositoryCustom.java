package com.davada.application.board.persistence.repository;

import com.davada.application.board.persistence.data.NoticeBoardData;
import com.davada.domain.board.condition.NoticeBoardCondition;

import java.util.List;

public interface NoticeBoardRepositoryCustom {
    List<NoticeBoardData> findListByCondition(NoticeBoardCondition condition, int offset, int limit);

    long countByCondition(NoticeBoardCondition condition);
}
