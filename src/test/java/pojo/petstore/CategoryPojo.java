package pojo.petstore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryPojo {

    private int id;
    private String name;

    @Override
    public String toString(){
        return "{\n" +
                "\"id\"=" + id + ",\n" +
                "\"name\"=\"" + name + "\"\n" +
                '}';
    }
}
