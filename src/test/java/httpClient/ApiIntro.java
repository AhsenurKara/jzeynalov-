package httpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiIntro {

    @Test
    public void firstApiCall() throws URISyntaxException, IOException {
        // Construct http client
        HttpClient httpClient = HttpClientBuilder.create().build();

        //Construct a request:
        //build an endpoint: https://petstore.swagger.io/v2/pet/183
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("petstore.swagger.io");
        uri.setPath("v2/pet/183");

        //construct a method (in this case get method)
        HttpGet httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept", "application/json");

        //execute a request (in this case it is get request)
        HttpResponse response = httpClient.execute(httpGet);

        System.out.println("Status code of my first api call is: " + response.getStatusLine().getStatusCode());

        assertEquals("Status code assertion failed", HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals("Invalid 'Content-Type' Header", "application/json",
                response.getEntity().getContentType().getValue());
    }

    @Test
    public void getUserInfo() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        //https://reqres.in/api/users/2
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users/2");

        HttpGet httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getEntity().getContentType().getValue());

        assertEquals("Status code assertion failed", HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertTrue("Invalid 'Content-Type' Header",
                response.getEntity().getContentType().getValue().contains("application/json"));


        /*
            Deserialization   -> Process of converting JSON format to Java format
            Serialization -> Process of converting Java format to JSON format
         */

        //Converting JSON Format to Java format (Deserialization)
        //creating ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //doing Deserialization
        Map<String, Object> deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        System.out.println(deserializedObject);

        System.out.println(deserializedObject.keySet());
        System.out.println(deserializedObject.size());

        Map<String, Object> dataValue = (Map<String, Object>) deserializedObject.get("data");

        System.out.println(dataValue.keySet());

        String email = (String) dataValue.get("email");

        System.out.println("User's name is: " + dataValue.get("first_name"));
        System.out.println("User's last name is: " + dataValue.get("last_name"));
        System.out.println("User's id is: " + dataValue.get("id"));
        System.out.println("User's email is: " + email);
        System.out.println("User's avatar is located: " + dataValue.get("avatar"));

        Map<String, Object> supportValue = (Map<String, Object>) deserializedObject.get("support");

        System.out.println("Support url is: " + supportValue.get("url"));
        System.out.println("Support text is: " + supportValue.get("text"));
    }
}
