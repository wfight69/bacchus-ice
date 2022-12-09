package com.davada.domain.common;

import com.davada.domain.common.util.JsonHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.JsonHelper.fromJsonArray;

public class NameValuePairs extends LinkedHashMap<String, String> {
    //
    protected NameValuePairs() {
        //
    }

    public static NameValuePairs newInstance() {
        return new NameValuePairs();
    }

    // Workaround - Issue of object type value conversion.
    public static NameValuePairs convertFromJson(String json) {

        JsonElement jsonElement = JsonParser.parseString(json);
        NameValuePairs nameValuePairs = NameValuePairs.newInstance();
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            jsonObject.keySet().forEach(key -> {
                JsonElement value = jsonObject.get(key);
                if (value.isJsonNull()) {
                    nameValuePairs.add(key, null);
                } else if (value.isJsonPrimitive()) {
                    nameValuePairs.add(key, value.getAsString());
                } else {
                    nameValuePairs.add(key, value.toString());
                }
            });
        }
        return nameValuePairs;
    }

    public NameValuePairs add(String name, Object value) {
        //
        String convertValue = null;
        if (value != null) {
            if (value instanceof String) {
                convertValue = (String) value;
            } else if (value.getClass().isPrimitive()) {
                convertValue = value.toString();
            } else if (value.getClass().isEnum()) {
                convertValue = ((Enum<?>) value).name();
            } else {
                convertValue = JsonHelper.toJson(value);
            }
        }

        put(name, convertValue);
        return this;
    }

    public NameValuePairs addIfExist(String name, Object value) {
        if (value != null) {
            add(name, value);
        }
        return this;
    }

    public String pullOut(String name) {
        return this.remove(name);
    }

    public void pullOut(String name, ValueHandler handler) {
        if (super.containsKey(name)) {
            handler.handle(super.remove(name));
        }
    }

    public void peek(String name, ValueHandler handler) {
        if (super.containsKey(name)) {
            handler.handle(super.get(name));
        }
    }

    public <T> List<T> getList(String name, Class<T[]> clazz) {
        return fromJsonArray(get(name), clazz);
    }

    public <T> T getObject(String name, Class<T> clazz) {
        return fromJson(get(name), clazz);
    }

    public List<String> extractNames() {
        return this.keySet().stream().collect(Collectors.toList());
    }

    @FunctionalInterface
    public interface ValueHandler {
        void handle(String value);
    }
}
