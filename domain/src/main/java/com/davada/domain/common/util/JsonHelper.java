package com.davada.domain.common.util;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

public class JsonHelper {
    private JsonHelper() {
        // Nothing to do.
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static String toJson(Object object) {
        return gson().toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (!isEmpty(jsonString)) {
            return gson().fromJson(jsonString, clazz);
        }
        return null;
    }

    public static <T> T fromJson(String jsonString, Type type) {
        if (!isEmpty(jsonString)) {
            return gson().fromJson(jsonString, type);
        }
        return null;
    }

    public static <T> List<T> fromJsonArray(String jsonString, Class<T[]> clazz) {
        if (!isEmpty(jsonString)) {
            Gson gson = gson();

            // [Workaround] fromJson으로 리스트를 변환할 때, 요소 타입이 LinkedTreeMap으로 바뀌는 문제로
            // 배열로 변환 후 다시 리스트로 변환
            T[] array = gson.fromJson(jsonString, clazz);
            return new ArrayList<>(Arrays.asList(array));
        }
        return new ArrayList<>();
    }

    public static <T> List<T> fromJsonArray(Reader reader, Class<T[]> clazz) {
        Gson gson = gson();

        // [Workaround] fromJson으로 리스트를 변환할 때, 요소 타입이 LinkedTreeMap으로 바뀌는 문제로
        // 배열로 변환 후 다시 리스트로 변환함
        T[] array = gson.fromJson(reader, clazz);
        return new ArrayList<>(Arrays.asList(array));
    }

    public static <K,V> Map<K,V> fromJsonMap(String jsonString, Class<K> k, Class<V> v) {
        if (!isEmpty(jsonString)) {
            Type type = new TypeToken<Map<K, V>>(){}.getType();
            return JsonHelper.fromJson(jsonString, type);
        }
        return new HashMap<>();
    }

    public static boolean equalsTo(Object obj1, Object obj2) {
        return gson().toJson(obj1).equals(gson().toJson(obj2));
    }

    public static String toPrettyJson(Object obj) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
    }

    // private methods

    private static JsonSerializer<Date> getJsonDateSerializer() {
        return (src, typeOfSrc, context) -> new JsonPrimitive(src.getTime());
    }

    private static JsonDeserializer<Date> getJsonDateDeserializer() {
        return (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong());
    }

    private static JsonSerializer<java.sql.Date> getJsonSqlDateSerializer() {
        return (src, typeOfSrc, context) -> new JsonPrimitive(src.getTime());
    }

    private static JsonDeserializer<java.sql.Date> getJsonSqlDateDeserializer() {
        return (json, typeOfT, context) -> new java.sql.Date(json.getAsJsonPrimitive().getAsLong());
    }

    private static Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, getJsonDateSerializer())
                .registerTypeAdapter(Date.class, getJsonDateDeserializer())
                .registerTypeAdapter(java.sql.Date.class, getJsonSqlDateSerializer())
                .registerTypeAdapter(java.sql.Date.class, getJsonSqlDateDeserializer())
                .create();
    }
}