package recipes.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.persistance.Recipe;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeModel implements BaseModel<Recipe> {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    private String[] ingredients;
    @NotEmpty
    private String[] directions;
    @NotBlank
    private String category;
    @Min(8)
    private String date;
    @JsonIgnore
    private String author;

    @Override
    public Recipe toDto() {
        Recipe recipe = new Recipe();
        recipe.setDescription(description);
        recipe.setName(name);
        recipe.setIngredients(Arrays.asList(ingredients));
        recipe.setDirections(Arrays.asList(directions));
        recipe.setCategory(category);
        recipe.setDate(date);
        return recipe;
    }
}
