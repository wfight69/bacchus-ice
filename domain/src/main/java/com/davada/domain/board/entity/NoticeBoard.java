package com.davada.domain.board.entity;

import com.davada.domain.board.vo.DisclosureScope;
import com.davada.domain.common.AuditableEntity;
import com.davada.domain.common.Refinable;
import com.davada.domain.common.vo.YN;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoard extends AuditableEntity implements Refinable {
    // UUID
    String noticeBoardUuid;
    // 주류도매업체 UUID
    String wholesalerUuid;
    // 작성자
    String employeeUuid;
    String employeeName;
    // 작성일자
    String issueDate;
    // 공개여부
    YN publicYn;
    // 공개범위 : 거래처,회사,전체
    DisclosureScope disclosureScope;
    // 조회수
    Integer views;
    // 전송여부
    YN transferYn;
    // 제목
    String title;
    // 본문
    String mainText;

    @Override
    public void refineValues() {
    }
}
