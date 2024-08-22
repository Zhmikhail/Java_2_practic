package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> readJSONFromFile(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), Map.class);
    }

    public static void writeJSONToFile(String filePath, Map<String, Object> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }
}
