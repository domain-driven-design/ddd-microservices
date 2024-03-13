package com.example.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

@Slf4j
public class JacksonUtil {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        MAPPER.registerModule(module);
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private JacksonUtil() {}

    public static String toJson(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("{} toJson error: ", value, e);
            throw new RuntimeException("Error converting object to JSON string", e);
        }
    }

    public static <T> T fromJsonToObject(String json, Class<T> valueType) {
        try {
            return MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("{} fromJsonToObject error: ", json, e);
            throw new RuntimeException("Error converting JSON string to object", e);
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> valueType) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, valueType));
        } catch (JsonProcessingException e) {
            log.error("{} fromJsonToList error: ", json, e);
            throw new RuntimeException("Error converting JSON string to list", e);
        }
    }

    public static <T> T[] fromJsonToArray(String json, Class<T[]> valueType) {
        try {
            return MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("{} fromJsonToArray error: ", json, e);
            throw new RuntimeException("Error converting JSON string to array", e);
        }
    }

    private static class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
        @Override
        public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException {
            return new BigDecimal(p.getValueAsString());
        }
    }

    private static class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.toPlainString());
        }
    }
}
