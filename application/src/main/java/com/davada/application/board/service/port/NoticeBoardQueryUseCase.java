package com.davada.application.board.service.port;

import com.davada.domain.board.condition.NoticeBoardCondition;
import com.davada.dto.board.NoticeBoardDto;

import java.util.List;

public interface NoticeBoardQueryUseCase {
    List<NoticeBoardDto> retrieveListByCondition(NoticeBoardCondition condition, int offset, int limit);

    long countByCondition(NoticeBoardCondition condition);
}
