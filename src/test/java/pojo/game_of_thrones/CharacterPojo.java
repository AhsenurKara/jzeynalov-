package pojo.game_of_thrones;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterPojo {

    private List<String> titles;
    private List<String> spouse;
    private List<String> children;
    private List<String> allegiance;
    private List<String> books;
    private double plod;
    private List<Double> longevity;
    private double plodB;
    private double plodC;
    private List<Double> longevityB;
    private List<Double> longevityC;
    private String _id;
    private String name;
    private String slug;
    private String gender;
    private String image;
    private String father;
    private String mother;
    private String heir;
    private String culture;
    private String house;
    private String placeOfBirth;
    private int birth;
    private String placeOfDeath;
    private int death;
    private boolean alive;
    private String createdAt;
    private String updatedAt;
    private int __v;
    private int longevityStartB;
    private PageRankPojo pagerank;
    private String id;
}

