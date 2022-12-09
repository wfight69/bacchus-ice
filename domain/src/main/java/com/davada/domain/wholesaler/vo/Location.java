package com.davada.domain.wholesaler.vo;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    String roadAddress;
    String roadAddressDetails;
    String landAddress;
    String landAddressDetails;
    String zipCode;
    Province province;

    float latitude;
    float longitude;
}
