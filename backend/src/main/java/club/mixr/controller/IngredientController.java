package club.mixr.controller;

import club.mixr.dto.Ingredient;
import club.mixr.dto.IngredientToCreate;
import club.mixr.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Ingredient> allIngredients() {
        return ingredientService.allIngredients();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Ingredient createIngredient(@RequestBody IngredientToCreate ingredientToCreate) {
        System.out.println(ingredientToCreate);
        return ingredientService.createIngredient(ingredientToCreate);
    }

}
