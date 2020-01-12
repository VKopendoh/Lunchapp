package com.vkopendoh.lunchapp.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.vkopendoh.lunchapp.web.json.JacksonObjectMapper.getMapper;

public class JsonUtil {

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = getMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public static  <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = getMapper();
        return objectMapper.readValue(json, clazz);
    }
}
