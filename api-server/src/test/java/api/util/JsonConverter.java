package api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    public static JsonNode toJsonNode(String jsonString) throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

}
