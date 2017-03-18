package club.mixr.service;

import club.mixr.data.entity.RecipeEntity;
import club.mixr.data.entity.RecipeIngredientEntity;
import club.mixr.data.entity.SourceEntity;
import club.mixr.data.repository.RecipeIngredientRepository;
import club.mixr.data.repository.RecipeRepository;
import club.mixr.data.repository.SourceRepository;
import club.mixr.dto.Recipe;
import club.mixr.dto.RecipeIngredient;
import club.mixr.dto.Source;
import club.mixr.dto.SourceToCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<Recipe> allRecipes() {
        return recipeRepository.findAll()
                .map(RecipeEntity::toRecipe)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Recipe> findAllRecipesByIngredientIds(List<Long> ids, int threshold) {
        Stream<RecipeIngredientEntity> recipeIngredients = recipeIngredientRepository.findAll();

        /**
         * We count how many times a recipe appears with a missing ingredient
         */
        Map<Long, Integer> badRecipes = recipeIngredients
                .filter(recipeIngredient -> !ids.contains(recipeIngredient.getIngredient().getId()))
                .collect(Collectors.toMap(r -> r.getRecipeId(), r -> 1, (a, b) -> a + b));


        Stream<RecipeEntity> recipes = recipeRepository.findAllRecipesByIngredientIds(ids);
        return recipes
                .filter(recipe -> badRecipes.getOrDefault((recipe.getId()), 0) <= threshold)
                .map(RecipeEntity::toRecipe)
                .collect(Collectors.toList());
    }

//    public Source createSource(SourceToCreate sourceToCreate) {
//        SourceEntity persisted = new SourceEntity(sourceToCreate.getName(), sourceToCreate.getDescription());
//        return sourceRepository.save(persisted).toSource();
//    }
}
