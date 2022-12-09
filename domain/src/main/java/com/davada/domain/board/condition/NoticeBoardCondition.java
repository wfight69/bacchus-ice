package com.davada.domain.board.condition;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardCondition {
    String wholesalerUuid;
    String employeeName;
    String mainText;
    Integer views;
}
