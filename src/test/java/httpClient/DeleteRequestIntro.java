package httpClient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class DeleteRequestIntro {

    @Test
    public void deleteIntro() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();

        // https://petstore.swagger.io/v2/pet/12343
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet/12343");

        HttpDelete delete = new HttpDelete(uri.build());

        HttpResponse response = client.execute(delete);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void deletePet() {
        int id = 435;

        HttpResponse response = HttpClientUtils.getPostResponse("https://petstore.swagger.io/v2/pet",
                PayLoadUtils.getNewPetPayload(id, "SomeName"));

        response = HttpClientUtils.getGetResponse("https://petstore.swagger.io/v2/pet/" + id);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        response = HttpClientUtils.getDeleteResponse("https://petstore.swagger.io/v2/pet/" + id);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        response = HttpClientUtils.getGetResponse("https://petstore.swagger.io/v2/pet/" + id);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusLine().getStatusCode());
    }
}
