package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetData {
    private String status;
    private String id;
    private Category category = new Category();
    private String name;
    private String[] photoUrls;
    private Tag tag = new Tag();

    @Getter
    @Setter
    public static class Category {
        String id;
        String name;
    }

    @Getter
    @Setter
    public static class Tag {
        String id;
        String name;
    }
}
