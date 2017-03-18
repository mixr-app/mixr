package club.mixr.controller;

import club.mixr.dto.Recipe;
import club.mixr.dto.RecipeIngredient;
import club.mixr.service.RecipeIngredientService;
import club.mixr.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes/")
public class RecipeIngredientController {

    @Autowired
    RecipeIngredientService recipeIngredientService;

    @RequestMapping(value = "/{id}/ingredients", method = RequestMethod.GET)
    public List<RecipeIngredient> getRecipeIngredients(@PathVariable Long id) {

        return recipeIngredientService.findByRecipeId(id);

    }

}
