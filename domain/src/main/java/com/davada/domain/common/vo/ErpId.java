package com.davada.domain.common.vo;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ErpId {
    private final UUID uuid;

    private ErpId(UUID uuid) {
        this.uuid = uuid;
    }

    public ErpId(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public static ErpId of(String id) {
        return new ErpId(UUID.fromString(id));
    }

    public static ErpId of(UUID uuid) {
        return new ErpId(uuid);
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    //FIXME: 변경.
    public static ErpId newId() {
        return new ErpId(UuidCreator.getTimeOrdered());
    }

    public String stripNode() {
        return this.uuid.toString().replaceAll("-", "").substring(0, 20);
    }
}
