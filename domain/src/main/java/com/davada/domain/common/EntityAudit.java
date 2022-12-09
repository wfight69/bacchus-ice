package com.davada.domain.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EntityAudit {
    Long timestamp; // timestamp of the request
    String userId;
    String ip;
}
