package club.mixr.controller;

import club.mixr.dto.Recipe;
import club.mixr.dto.RecipeWithIngredients;
import club.mixr.dto.Source;
import club.mixr.dto.SourceToCreate;
import club.mixr.service.RecipeService;
import club.mixr.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public List<Recipe> allRecipes(
            @RequestParam(value = "ids", required = false) List<Long> ids,
            @RequestParam(value = "threshold", defaultValue = "0") int threshold) {

        return (ids == null) ? recipeService.allRecipes()
                : recipeService.findAllRecipesByIngredientIds(ids, threshold);
    }

    @RequestMapping(value = "/recipesWithIngredients", method = RequestMethod.GET)
    public List<RecipeWithIngredients> allRecipesWithIngredients(
            @RequestParam(value = "ids", required = false) List<Long> ids,
            @RequestParam(value = "threshold", defaultValue = "0") int threshold) {

        return recipeService.allRecipesWithIngredients();
    }

//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public Source createIngredient(@RequestBody SourceToCreate sourceToCreate) {
//        return sourceService.createSource(sourceToCreate);
//    }

}
