package club.mixr.controller;

import club.mixr.dto.Recipe;
import club.mixr.dto.Source;
import club.mixr.dto.SourceToCreate;
import club.mixr.service.RecipeService;
import club.mixr.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes/")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Recipe> allRecipes() {
        return recipeService.allRecipes();
    }

//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public Source createIngredient(@RequestBody SourceToCreate sourceToCreate) {
//        return sourceService.createSource(sourceToCreate);
//    }

}
