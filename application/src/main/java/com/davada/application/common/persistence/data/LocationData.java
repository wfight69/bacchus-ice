package com.davada.application.common.persistence.data;

import com.davada.domain.wholesaler.vo.Province;
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
public class LocationData {
    private String roadAddress;
    private String roadAddressDetails;
    private String landAddress;
    private String landAddressDetails;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private Province province;

    private float latitude;
    private float longitude;
}
