package recipes.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.service.AdditionResult;
import recipes.persistance.Recipe;
import recipes.service.RecipeService;
import recipes.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RecipeApiController {
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("recipe/{id}")
    public RecipeModel get(final @PathVariable Integer id) {
        return recipeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("recipe/new")
    public AdditionResult add(@RequestBody @Valid RecipeModel recipe, Principal principal) {
        String email = principal.getName();
        recipe.setAuthor(email);
        return recipeService.save(recipe);
    }

    @PutMapping("recipe/{id}")
    public ResponseEntity<Void> update(@PathVariable int id,
                                       @RequestBody @Valid RecipeModel recipe,
                                       Principal principal) {
        Optional<RecipeModel> updatedRecipe = recipeService.findById(id);
        if (updatedRecipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            if (!updatedRecipe.get().getAuthor().equalsIgnoreCase(principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        recipe.setAuthor(updatedRecipe.get().getAuthor());
        recipeService.update(id, recipe);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("recipe/search")
    public List<RecipeModel> update(@RequestParam(required = false) String category,
                                    @RequestParam(required = false) String name) {
        if (category != null && name != null || category == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (category != null) {
            return recipeService.findByCategory(category);
        }
        return recipeService.findByName(name);
    }

    @DeleteMapping("recipe/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id, Principal principal) {
        final Optional<RecipeModel> recipeToDelete = recipeService.findById(id);
        if (recipeToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            final String actualAuthor = recipeToDelete.get().getAuthor();
            String email = principal.getName();
            log.info("Actual author: {}, req author: {}",
                    actualAuthor, email);
            if (actualAuthor == null || !actualAuthor.equalsIgnoreCase(email)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
