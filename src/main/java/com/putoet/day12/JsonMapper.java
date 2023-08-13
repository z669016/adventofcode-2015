package com.putoet.day12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.putoet.resources.ResourceLines;

import java.util.Map;

class JsonMapper {
    public static Map<String, Object> fromJson(String resourceName) {
        try {
            final var data = ResourceLines.line(resourceName);
            final var json = "{\"data\":" + data + "}";
            final var mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Couldn't read or parse '" + resourceName + "'", e);
        }
    }
}