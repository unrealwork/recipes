package recipes.persistance;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(final String category);
    List<Recipe> findByNameContainsIgnoreCaseOrderByDateDesc(final String name);
}
