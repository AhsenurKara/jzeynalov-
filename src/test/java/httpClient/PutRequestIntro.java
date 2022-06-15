package httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class PutRequestIntro {

    @Test
    public void putRequest1() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        // https://petstore.swagger.io/v2/pet
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");

        HttpPut put = new HttpPut(uri.build());
        put.setHeader("Accept", "application/json");
        put.setHeader("Content-Type", "application/json");

        HttpEntity entity = new StringEntity(PayLoadUtils.getNewPetPayload(183, "Reks"));

        put.setEntity(entity);

        HttpResponse response = client.execute(put);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void updatePet(){
        int id = 1994;
        String name = "Kapibara";

        HttpResponse response = HttpClientUtils.getPostResponse("https://petstore.swagger.io/v2/pet", PayLoadUtils.getNewPetPayload(id, name));
        Map<String,Object> body = HttpClientUtils.getResponseBody(response);
        Assert.assertEquals(name, body.get("name"));

        name = "David";
        response = HttpClientUtils.getPutResponse("https://petstore.swagger.io/v2/pet", PayLoadUtils.getNewPetPayload(id, name));
        body = HttpClientUtils.getResponseBody(response);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertEquals(name, body.get("name"));

        response = HttpClientUtils.getGetResponse("https://petstore.swagger.io/v2/pet/" + id);
        body = HttpClientUtils.getResponseBody(response);
        Assert.assertEquals(name, body.get("name"));
    }
}
