package com.davada.domain.common.vo;

import java.util.HashMap;

public class IdValue extends HashMap<String, String> {
    protected IdValue() {
        super();
    }

    public IdValue(String attrName, String value) {
        put(attrName, value);
    }

    public String idValue() {
        return values().stream().findFirst().orElse(null);
    }
}
