package club.mixr.controller;

import club.mixr.dto.*;
import club.mixr.service.RecipeService;
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

        return (ids == null) ? recipeService.findAllRecipes()
                : recipeService.findAllRecipesByIngredientIds(ids, threshold);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/recipes", method = RequestMethod.POST)
    public Recipe createRecipe(@RequestBody RecipeToCreate recipeToCreate) {
        System.out.println("Saving " + recipeToCreate.toString());
        return recipeService.createRecipe(recipeToCreate);
    }

}
