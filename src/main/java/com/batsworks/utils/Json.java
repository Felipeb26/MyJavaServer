package com.batsworks.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {

    private static ObjectMapper mapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String jsonString) throws IOException {
        return mapper.readTree(jsonString);
    }

    public static <A> A fromJson(JsonNode jsonNode, Class<A> aClass) throws JsonProcessingException {
        return mapper.treeToValue(jsonNode, aClass);
    }

    public static JsonNode toJSon(Object object) {
        return mapper.valueToTree(object);
    }

    public static String stringfyPretty(JsonNode jsonNode) throws JsonProcessingException {
        return generateJson(jsonNode, true);
    }

    public static String stringfy(JsonNode jsonNode) throws JsonProcessingException {
        return generateJson(jsonNode, false);
    }

    private static String generateJson(Object o, Boolean pretty) throws JsonProcessingException {
        ObjectWriter writer = mapper.writer();
        if (pretty) {
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        }
        return writer.writeValueAsString(o);
    }

}
