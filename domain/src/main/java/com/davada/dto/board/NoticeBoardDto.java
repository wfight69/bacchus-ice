package com.davada.dto.board;


import com.davada.domain.board.vo.DisclosureScope;
import com.davada.domain.common.vo.YN;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardDto {
    String noticeBoardUuid;
    String wholesalerUuid;
    String employeeUuid;
    String employeeName;
    String issueDate;
    YN publicYn;
    DisclosureScope disclosureScope;
    Integer views;
    YN transferYn;
    String title;
    String mainText;
}
