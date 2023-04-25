package cn.edu.xmut.learningplatform.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> valueType) throws Exception {
        return objectMapper.readValue(json, valueType);
    }
}

