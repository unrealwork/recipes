package recipes.persistance;

import lombok.Data;
import recipes.presentation.RecipeModel;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Recipe implements BaseDTO<RecipeModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;
    @ElementCollection
    private List<String> ingredients;
    @ElementCollection
    private List<String> directions;
    private String category;
    private String date;
    private String author;


    @Override
    public RecipeModel toModel() {
        return new RecipeModel(name, description,
                ingredients.toArray(String[]::new),
                directions.toArray(String[]::new),
                category, date, author
        );
    }
}
