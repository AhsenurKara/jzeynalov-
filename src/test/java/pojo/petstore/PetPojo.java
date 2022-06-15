package pojo.petstore;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetPojo {

    //success
    private int id;
    private CategoryPojo category;
    private String name;
    private List<String> photoUrls;
    private List<TagPojo> tags;
    private String status;

    @Override
    public String toString() {
        return "{\n" +
                "\"id\"=" + id + ",\n" +
                "\"category\"=" + category + ",\n" +
                "\"name\"=\"" + name + ",\n" +
                "\"photoUrls=" + photoUrls + ",\n" +
                "\"tags\"=" + tags + ",\n" +
                "\"status\"=\"" + status + "\n" +
                '}';
    }
}
