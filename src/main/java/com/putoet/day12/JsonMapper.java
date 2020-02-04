package com.putoet.day12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.ResourceLines;

import java.util.List;
import java.util.Map;

public class JsonMapper {
    public static Map<String, Object> fromJson(String resourceName) {
        try {
            final List<String> list = ResourceLines.list(resourceName);
            final String json = "{\"data\":" + list.get(0) + "}";

            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Couldn't read or parse '" + resourceName + "'", e);
        }
    }
}