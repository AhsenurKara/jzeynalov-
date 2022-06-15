package httpClient;

import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GotApi {

    public static List<String> collectByConditions(List<Map<String, Object>> characters, String checkKey,
                                                   boolean checkValue, String collectKey) {
        List<String> result = new ArrayList<>();

        for (Map<String, Object> character : characters) {
            if ((boolean) character.get(checkKey) == checkValue) {
                result.add((String) character.get(collectKey));
            }
        }

        return result;
    }

    public static List<String> collectByConditions(List<Map<String, Object>> characters, String checkKey,
                                                   int checkValue, String collectKey) {
        List<String> result = new ArrayList<>();

        for (Map<String, Object> character : characters) {
            if ((int) character.get(checkKey) == checkValue) {
                result.add((String) character.get(collectKey));
            }
        }

        return result;
    }

    public static List<String> collectByConditions(List<Map<String, Object>> characters, String checkKey,
                                                   String checkValue, String collectKey) {
        List<String> result = new ArrayList<>();

        for (Map<String, Object> character : characters) {
            if (checkValue != null) {
                if ((character.get(checkKey)).equals(checkValue)) {
                    result.add((String) character.get(collectKey));
                }
            } else {
                if ((character.get(checkKey)) == checkValue) {
                    result.add((String) character.get(collectKey));
                }
            }
        }

        return result;
    }

    public static List<String> collectByConditionsKeyExists(List<Map<String, Object>> characters, String checkKey,
                                                            boolean valueExists, String collectKey) {
        List<String> result = new ArrayList<>();

        for (Map<String, Object> character : characters) {
            if (valueExists) {
                if (character.get(checkKey) != null) {
                    result.add((String) character.get(collectKey));
                }
            } else {
                if (character.get(checkKey) == null) {
                    result.add((String) character.get(collectKey));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getMaleCharactersNames());
        System.out.println(getFemaleCharactersNames());
        System.out.println(getAliveCharactersNames());
        System.out.println(getDeadCharactersNames());

    }

    public static List<String> getMaleCharactersNames() {
        HttpResponse response = HttpClientUtils.getGetResponse("https", "api.got.show", "api/map/characters", null);
        Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

        List<Map<String, Object>> characters = (List<Map<String, Object>>) deserializedObject.get("data");

        return GotApi.collectByConditions(characters, "male", true, "name");
    }

    public static List<String> getFemaleCharactersNames() {
        HttpResponse response = HttpClientUtils.getGetResponse("https", "api.got.show", "api/map/characters", null);
        Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

        List<Map<String, Object>> characters = (List<Map<String, Object>>) deserializedObject.get("data");
        return GotApi.collectByConditions(characters, "male", false, "name");
    }

    public static List<String> getAliveCharactersNames() {
        HttpResponse response = HttpClientUtils.getGetResponse("https", "api.got.show", "api/map/characters", null);
        Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

        List<Map<String, Object>> characters = (List<Map<String, Object>>) deserializedObject.get("data");

        return GotApi.collectByConditionsKeyExists(characters, "dateOfDeath", false, "name");
    }

    public static List<String> getDeadCharactersNames() {
        HttpResponse response = HttpClientUtils.getGetResponse("https", "api.got.show", "api/map/characters", null);
        Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

        List<Map<String, Object>> characters = (List<Map<String, Object>>) deserializedObject.get("data");

        return GotApi.collectByConditionsKeyExists(characters, "dateOfDeath", true, "name");
    }

}
