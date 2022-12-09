package com.davada.application.employee.persistence.data;

import com.davada.domain.wholesaler.vo.GenderType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalDetailsData {
    String mobilePhone;
    String homeTelephone;
    String email;
    String address;
    String addressDetails;
    String zipCode;
    @Enumerated(EnumType.STRING)
    GenderType gender;
    String foreignerYn;
}
