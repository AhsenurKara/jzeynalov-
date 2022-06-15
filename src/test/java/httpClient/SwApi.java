package httpClient;

import org.apache.http.HttpResponse;

import java.util.*;

public class SwApi {

    /*
        Star Wars API -> get name of biggest planet
        Star Wars API -> get all planets names with "temperate" climate
        Star Wars API -> get straship name of most length
        Star Wars API -> get all starship names with "Starfighter" class
     */
    public static void main(String[] args) {
        System.out.println(biggestPlanet());
        System.out.println(temperatePlanet());
        System.out.println(longestStarship());
        System.out.println(starfighterStarships());
        System.out.println(sortStarshipsByPilots());
        System.out.println(starshipsByLength());
    }

    public static String biggestPlanet() {
        List<Map<String, Object>> planets = new ArrayList<>();
        boolean continuePlanetCheck = true;
        int page = 1;

        while (continuePlanetCheck) {

            HttpResponse response =
                    HttpClientUtils.getGetResponse("https", "swapi.dev", "api/planets", new String[]{"page=" + page++});

            Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

            planets.addAll((List<Map<String, Object>>) deserializedObject.get("results"));

            if (deserializedObject.get("next") == null) {
                continuePlanetCheck = false;
            }
        }

        String biggestPlanet = "";
        int maxDiameter = 0;

        for (Map<String, Object> planet : planets) {
            if (planet.get("diameter").equals("unknown")) {
                continue;
            }
            int currentDiameter = Integer.parseInt((String) planet.get("diameter"));

            if (currentDiameter > maxDiameter) {
                maxDiameter = currentDiameter;
                biggestPlanet = (String) planet.get("name");
            }
        }

        return biggestPlanet;
    }

    public static List<String> temperatePlanet() {
        List<Map<String, Object>> planets = new ArrayList<>();
        boolean continuePlanetCheck = true;
        int page = 1;

        while (continuePlanetCheck) {

            HttpResponse response =
                    HttpClientUtils.getGetResponse("https", "swapi.dev", "api/planets", new String[]{"page=" + page++});

            Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

            planets.addAll((List<Map<String, Object>>) deserializedObject.get("results"));

            if (deserializedObject.get("next") == null) {
                continuePlanetCheck = false;
            }
        }

        List<String> temperatePlanet = new ArrayList<>();

        for (Map<String, Object> planet : planets) {
            if (planet.get("climate").equals("temperate")) {
                temperatePlanet.add((String) planet.get("name"));
            }
        }

        return temperatePlanet;
    }

    public static String longestStarship() {
        List<Map<String, Object>> starships = new ArrayList<>();
        boolean continueStarshipCheck = true;
        int page = 1;

        while (continueStarshipCheck) {

            HttpResponse response =
                    HttpClientUtils.getGetResponse("https", "swapi.dev", "api/starships/",
                            new String[]{"page=" + page++});

            Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

            starships.addAll((List<Map<String, Object>>) deserializedObject.get("results"));

            if (deserializedObject.get("next") == null) {
                continueStarshipCheck = false;
            }
        }

        String longestStarship = "";
        double maxLength = 0;

        for (Map<String, Object> starship : starships) {
            double currentLength = Double.parseDouble(((String) starship.get("length")).replaceAll("[^0-9.]", ""));

            if (currentLength > maxLength) {
                maxLength = currentLength;
                longestStarship = (String) starship.get("name");
            }
        }

        return longestStarship;
    }

    public static List<String> starfighterStarships() {
        List<Map<String, Object>> starships = new ArrayList<>();
        boolean continueStarshipCheck = true;
        int page = 1;

        while (continueStarshipCheck) {

            HttpResponse response =
                    HttpClientUtils.getGetResponse("https", "swapi.dev", "api/starships/",
                            new String[]{"page=" + page++});

            Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

            starships.addAll((List<Map<String, Object>>) deserializedObject.get("results"));

            if (deserializedObject.get("next") == null) {
                continueStarshipCheck = false;
            }
        }

        List<String> starfighters = new ArrayList<>();

        for (Map<String, Object> starship : starships) {
            if (starship.get("starship_class").equals("Starfighter")) {
                starfighters.add((String) starship.get("name"));
            }
        }

        return starfighters;
    }

    /*
        Task#2
        Star Wars API -> get all people, and create another Map<String, List<Map<String, Object>>>, where you will
        sort all starships by pilots.

        Map<String, Object> -> straship information
        <List<Map<String, Object>> -> list of starships controled by this character
        Map<String, List<Map<String, Object>>> -> map of starships, by pilot name
     */
    public static Map<String, List<Map<String, Object>>> sortStarshipsByPilots() {
        //   Names of pilots  List of information about starship
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        List<Map<String, Object>> people = new ArrayList<>();
        boolean continuePeopleCheck = true;
        int page = 1;

        while (continuePeopleCheck) {

            HttpResponse response =
                    HttpClientUtils.getGetResponse("https", "swapi.dev", "api/people", new String[]{"page=" + page++});

            Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

            people.addAll((List<Map<String, Object>>) deserializedObject.get("results"));


            if (deserializedObject.get("next") == null) {
                continuePeopleCheck = false;
            }
        }

        for (Map<String, Object> person : people) {
            List<String> starships = (List<String>) person.get("starships");

            if (!starships.isEmpty()) {
                String pilotName = (String) person.get("name");
                List<Map<String, Object>> starshipsByPilot = new ArrayList<>();

                for (String starship : starships) {
                    HttpResponse response = HttpClientUtils.getGetResponse(starship);
                    Map<String, Object> starshipInfo = HttpClientUtils.getResponseBody(response);
                    starshipsByPilot.add(starshipInfo);
                }

                result.put(pilotName, starshipsByPilot);

            }
        }

        return result;
    }

    /*
        Task#3
        List<String> -> that will contain all straship names, ordered by its length in ascending order (from smallest
         to larger)
     */
    public static List<String> starshipsByLength() {
        List<Map<String, Object>> starships = new ArrayList<>();
        boolean continueStarshipCheck = true;
        int page = 1;

        while (continueStarshipCheck) {
            HttpResponse response =
                    HttpClientUtils.getGetResponse("https", "swapi.dev", "api/starships/",
                            new String[]{"page=" + page++});

            Map<String, Object> deserializedObject = HttpClientUtils.getResponseBody(response);

            starships.addAll((List<Map<String, Object>>) deserializedObject.get("results"));

            if (deserializedObject.get("next") == null) {
                continueStarshipCheck = false;
            }
        }

        //      length  name
        TreeMap<Double, String> mapsByLength = new TreeMap<>();

        for (Map<String, Object> starship : starships) {
            double currentLength = Double.parseDouble(((String) starship.get("length")).replaceAll("[^0-9.]", ""));
            String starshipName = (String) starship.get("name");

            mapsByLength.put(currentLength, starshipName);
        }

        List<String> result = new ArrayList<>();

       for(Double length : mapsByLength.keySet()){
           result.add(mapsByLength.get(length));
       }

        return result;
    }
}
