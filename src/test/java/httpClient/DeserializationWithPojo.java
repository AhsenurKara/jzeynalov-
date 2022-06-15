package httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import pojo.game_of_thrones.CharacterPojo;
import pojo.game_of_thrones.HousesPojo;
import pojo.petstore.PetPojo;
import pojo.reqres.UserPojo;

import java.io.IOException;
import java.util.List;

public class DeserializationWithPojo {


    @Test
    public void getUser() throws IOException {
        HttpResponse response = HttpClientUtils.getGetResponse("https://reqres.in/api/users/2");

        ObjectMapper objectMapper = new ObjectMapper();
        UserPojo user = objectMapper.readValue(response.getEntity().getContent(), UserPojo.class);

        System.out.println(user.getData().getId());
        System.out.println(user.getSupport().getText());
        System.out.println(user.getData().getFirst_name());
    }

    @Test
    public void getPet() throws IOException {
        HttpResponse response = HttpClientUtils.getGetResponse("https://petstore.swagger.io/v2/pet/12343");

        ObjectMapper objectMapper = new ObjectMapper();
        PetPojo pet = objectMapper.readValue(response.getEntity().getContent(), PetPojo.class);

        System.out.println(pet);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            System.out.println(pet.getName());
            System.out.println(pet.getPhotoUrls().get(0));
            System.out.println(pet.getCategory().getName());
            System.out.println(pet.getTags().get(0).getName());
            System.out.println(pet.getTags().get(1));
        }else {
            System.out.println("Failure");
        }

    }

    @Test
    public void namesFromGOT() throws IOException {
        HttpResponse response = HttpClientUtils.getGetResponse("https://api.got.show/api/book/characters");

        ObjectMapper objectMapper = new ObjectMapper();
        CharacterPojo[] characters = objectMapper.readValue(response.getEntity().getContent(), CharacterPojo[].class);

        for(CharacterPojo character: characters){
            System.out.println(character.getName());
        }
    }

    @Test
    public void housesFromGOT() throws IOException{
        HttpResponse response = HttpClientUtils.getGetResponse("https://api.got.show/api/book/houses");

        ObjectMapper objectMapper = new ObjectMapper();
        HousesPojo[] houses = objectMapper.readValue(response.getEntity().getContent(),HousesPojo[].class);

        for(HousesPojo house: houses){
            System.out.println(house.getName());
        }
    }
}
