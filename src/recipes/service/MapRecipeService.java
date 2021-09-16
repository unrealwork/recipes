package recipes.service;

import recipes.persistance.Recipe;
import recipes.presentation.RecipeModel;

import java.util.List;
import java.util.Optional;

public interface MapRecipeService {
    AdditionResult save(RecipeModel recipe);

    void update(int id, RecipeModel recipe);

    Optional<RecipeModel> findById(int id);

    List<RecipeModel> findByCategory(String category);

    List<RecipeModel> findByName(String name);

    void deleteById(int id);
}
