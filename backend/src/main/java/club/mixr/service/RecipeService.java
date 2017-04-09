package club.mixr.service;

import club.mixr.data.entity.RecipeEntity;
import club.mixr.data.entity.RecipeIngredientEntity;
import club.mixr.data.entity.SourceEntity;
import club.mixr.data.repository.RecipeIngredientRepository;
import club.mixr.data.repository.RecipeRepository;
import club.mixr.data.repository.SourceRepository;
import club.mixr.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jdifebo on 3/14/2017.
 */
@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    EntityManager entityManager;

    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll()
                .map(RecipeEntity::toRecipe)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Recipe> findAllRecipesByIngredientIds(List<Long> ids, int threshold) {
        Stream<RecipeIngredientEntity> recipeIngredients = recipeIngredientRepository.findAll();

        /*
         * We count how many times a recipe appears with a missing ingredient
         */
        Map<Long, Integer> badRecipes = recipeIngredients
                .filter(recipeIngredient -> !ids.contains(recipeIngredient.getIngredient().getId()))
                .collect(Collectors.toMap(RecipeIngredientEntity::getRecipeId, r -> 1, (a, b) -> a + b));


        Stream<RecipeEntity> recipes = recipeRepository.findAllRecipesByIngredientIds(ids);
        return recipes
                .filter(recipe -> badRecipes.getOrDefault((recipe.getId()), 0) <= threshold)
                .map(RecipeEntity::toRecipe)
                .collect(Collectors.toList());
    }



    @Transactional
    public Recipe createRecipe(RecipeToCreate recipeToCreate) {
        RecipeEntity recipeToSave = new RecipeEntity(recipeToCreate.getName(), recipeToCreate.getDescription(),
                recipeToCreate.getInstructions(), recipeToCreate.getImageLocation(), recipeToCreate.getSourceId());

        List<RecipeIngredientEntity> ingredientsToSave = recipeToCreate.getIngredients().stream()
                .map(recipeIngredientToCreate -> new RecipeIngredientEntity(
                        recipeToSave,
                        recipeIngredientToCreate.getIngredientId(),
                        recipeIngredientToCreate.getAmount(),
                        recipeIngredientToCreate.getUnit()))
                .collect(Collectors.toList());

        recipeToSave.setRecipeIngredients(ingredientsToSave);

        RecipeEntity persisted = recipeRepository.save(recipeToSave);

        return persisted.toRecipe();
    }

}
