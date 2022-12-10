package com.davada.application.board.service.port;

import com.davada.domain.board.vo.DisclosureScope;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.vo.BooleanValue;
import com.davada.domain.common.vo.IdValue;
import com.davada.domain.common.vo.YN;
import com.davada.dto.board.NoticeBoardDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public interface NoticeBoardCrudUseCase {

    IdValue createNoticeBoard(CreateNoticeBoardCommand command);

    NoticeBoardDto retrieveNoticeBoard(String noticeBoardUuid);

    List<NoticeBoardDto> retrieveAllNoticeBoard(String wholesalerUuid);

    BooleanValue updateNoticeBoard(String noticeBoardUuid, NameValuePairs nameValuePairs);

    BooleanValue deleteNoticeBoard(String noticeBoardUuid, boolean permanent);

    BooleanValue deleteNoticeBoards(DeleteNoticeBoardCommand command);

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class CreateNoticeBoardCommand {
        @NotBlank(message = "주류도매업체 아이디는 필수 입력입니다.")
        String wholesalerUuid;
        @NotBlank(message = "작성자 아이디는 필수 입력입니다.")
        String employeeUuid;
        @NotBlank(message = "작성자는 필수 입력입니다.")
        String employeeName;
        String issueDate;
        @NotNull(message = "공개여부는 필수 입력입니다.")
        YN publicYn;
        @NotNull(message = "공개범위는 필수 입력입니다.")
        DisclosureScope disclosureScope;
        @NotNull(message = "전송여부는 필수 입력입니다.")
        YN transferYn;
        @NotBlank(message = "제목은 필수 입력입니다.")
        String title;
        @NotBlank(message = "본문은는 필수 입력입니다.")
        String mainText;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class DeleteNoticeBoardCommand {
        Set<String> noticeBoardUuids;
        boolean permanent;
    }
}
