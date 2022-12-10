package com.davada.application.board.service;

import com.davada.application.board.service.port.NoticeBoardCrudUseCase;
import com.davada.domain.board.entity.NoticeBoard;
import com.davada.dto.board.NoticeBoardDto;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface NoticeBoardMapper {
    @Mapping(target = "auditLog", ignore = true)
    NoticeBoard toDomain(String noticeBoardUuid, Integer views, String issueDate, NoticeBoardCrudUseCase.CreateNoticeBoardCommand command);

    NoticeBoardDto toNoticeBoardDto(NoticeBoard noticeBoard);
}
