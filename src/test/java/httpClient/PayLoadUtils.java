package httpClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayLoadUtils {

    public static String getReqresUserPayload(String name, String job) {
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + job + "\"\n" +
                "}";
    }

    public static String getNewPetPayload(int id, String name) {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 11,\n" +
                "    \"name\": \"Husky\"\n" +
                "  },\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 132,\n" +
                "      \"name\": \"Service Dog\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"NOT available\"\n" +
                "}";
    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
