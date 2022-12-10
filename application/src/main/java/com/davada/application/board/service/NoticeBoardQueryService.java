package com.davada.application.board.service;

import com.davada.application.board.persistence.NoticeBoardPersistenceAdapter;
import com.davada.application.board.service.port.NoticeBoardQueryUseCase;
import com.davada.domain.board.condition.NoticeBoardCondition;
import com.davada.dto.board.NoticeBoardDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class NoticeBoardQueryService implements NoticeBoardQueryUseCase {
    private final NoticeBoardPersistenceAdapter noticeBoardQueryPort;
    private final NoticeBoardMapper noticeBoardMapper;

    @Override
    public List<NoticeBoardDto> retrieveListByCondition(NoticeBoardCondition condition, int offset, int limit) {
        return noticeBoardQueryPort.retrieveListByCondition(condition, offset, limit)
                .stream()
                .map(noticeBoardMapper::toNoticeBoardDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countByCondition(NoticeBoardCondition condition) {
        return noticeBoardQueryPort.countByCondition(condition);
    }
}
