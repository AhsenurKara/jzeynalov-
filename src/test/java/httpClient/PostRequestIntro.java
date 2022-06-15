package httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class PostRequestIntro {

    @Test
    public void postRequest() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // https://reqres.in/api/users
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users");

        HttpPost post = new HttpPost(uri.build());
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        HttpEntity entity = new StringEntity("{\n" +
                "    \"name\": \"John\",\n" +
                "    \"job\": \"Software Tester\"\n" +
                "}");

        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        Map<String, Object> body = HttpClientUtils.getResponseBody(response);
        System.out.println(body);
    }

    @Test
    public void postRequest2() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        // https://reqres.in/api/users
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https").setHost("reqres.in").setPath("api/users");

        HttpPost post = new HttpPost(uri.build());
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        String payload = PayLoadUtils.getReqresUserPayload("Doe", "QA Manager");

        System.out.println(payload);
        HttpEntity entity = new StringEntity(payload);

        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        Map<String, Object> body = HttpClientUtils.getResponseBody(response);
        System.out.println(body);
    }

    @Test
    public void postRequest3() {
        String payload = PayLoadUtils.getReqresUserPayload("Patel", "Manual QA");

        HttpResponse response = HttpClientUtils.getPostResponse("https://reqres.in/api/users", payload);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        Map<String, Object> body = HttpClientUtils.getResponseBody(response);
        System.out.println(body);
    }

    @Test
    public void postRequest4() throws IOException {
        String payload = PayLoadUtils.generateStringFromResource("src/test/resources/payloads/NewReqresUser.json");

        HttpResponse response = HttpClientUtils.getPostResponse("https://reqres.in/api/users", payload);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        Map<String, Object> body = HttpClientUtils.getResponseBody(response);
        System.out.println(body);
    }

    @Test
    public void createPetEndpointTest() {
        int petId = 1645;
        String petName = "Reks";

        HttpResponse response = HttpClientUtils.getPostResponse("https://petstore.swagger.io/v2/pet",
                PayLoadUtils.getNewPetPayload(petId, petName));

        Assert.assertEquals("Status code is wrong", HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        Map<String, Object> postResponseBody = HttpClientUtils.getResponseBody(response);

        Assert.assertEquals("Id is wrong", petId, postResponseBody.get("id"));
        Assert.assertEquals("Name is wrong", petName, postResponseBody.get("name"));

        response = HttpClientUtils.getGetResponse("https://petstore.swagger.io/v2/pet/" + petId);

        Assert.assertEquals("Status code is wrong", HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        Map<String, Object> getResponseBody = HttpClientUtils.getResponseBody(response);

        Assert.assertEquals("Id is wrong", petId, getResponseBody.get("id"));
        Assert.assertEquals("Name is wrong", petName, getResponseBody.get("name"));
    }
}
