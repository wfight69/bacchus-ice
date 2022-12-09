package com.davada.domain.wholesaler.entity;

import com.davada.domain.common.Refinable;
import com.davada.domain.wholesaler.vo.GenderType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalDetails implements Refinable {
    //핸드폰번호
    String mobilePhone;
    //집전화번호
    String homeTelephone;
    //개인이메일
    String email;
    //집주소
    String address;
    //집주소 상세
    String addressDetails;
    //우편번호
    String zipCode;
    //성별
    GenderType gender;
    //외국인여부
    String foreignerYn;

    @Override
    public void refineValues() {
    }
}
