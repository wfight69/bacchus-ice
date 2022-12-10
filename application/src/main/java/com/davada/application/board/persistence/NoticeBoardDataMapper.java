package com.davada.application.board.persistence;

import com.davada.application.board.persistence.data.NoticeBoardData;
import com.davada.domain.board.entity.NoticeBoard;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface NoticeBoardDataMapper {
    @Mapping(target = "version", ignore = true)
    NoticeBoardData toData(NoticeBoard noticeBoard);

    NoticeBoard fromData(NoticeBoardData noticeBoardData);
}
