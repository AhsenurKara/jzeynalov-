package httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import pojo.petstore.CategoryPojo;
import pojo.petstore.PetPojo;
import pojo.petstore.TagPojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializationWithPojo {

    @Test
    public void serializePet() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://petstore.swagger.io/v2/pet");
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        /*
        {
            "id": 12343,
            "category": {
                "id": 11,
                "name": "Husky"
            },
            "name": "Luna",
            "photoUrls": [
                "www.picture.com/img1213"
            ],
            "tags": [
                {
                    "id": 132,
                    "name": "Service Dog"
                },
                {
                    "id": 12,
                    "name": "Champion"
                }
            ],
            "status": "NOT available"
        }
        */

        CategoryPojo category = new CategoryPojo();
        category.setId(11);
        category.setName("Husky");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("www.picture.com/img1213");

        TagPojo tag1 = new TagPojo();
        tag1.setId(132);
        tag1.setName("Service Dog");

        TagPojo tag2 = new TagPojo();
        tag2.setId(12);
        tag2.setName("Champion");

        List<TagPojo> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);

        PetPojo pet = new PetPojo();
        pet.setId(123456);
        pet.setCategory(category);
        pet.setName("Luna");
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus("NOT available");


        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/payloads/pet.json");
        objectMapper.writeValue(file, pet);

        //System.out.print(objectMapper.writeValueAsString(pet));

        post.setEntity(new StringEntity(objectMapper.writeValueAsString(pet)));

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

    }
}
