package club.mixr.service;

import club.mixr.data.entity.RecipeEntity;
import club.mixr.data.entity.RecipeIngredientEntity;
import club.mixr.data.repository.RecipeIngredientRepository;
import club.mixr.data.repository.RecipeRepository;
import club.mixr.dto.Recipe;
import club.mixr.dto.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jdifebo on 3/14/2017.
 */
@Service
public class RecipeIngredientService {

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    public List<RecipeIngredient> allRecipeIngredients() {
        return recipeIngredientRepository.findAll()
                .map(RecipeIngredientEntity::toRecipeIngredient)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<RecipeIngredient> findByRecipeId(Long id) {
        return recipeIngredientRepository.findByRecipeId(id)
                .map(RecipeIngredientEntity::toRecipeIngredient)
                .collect(Collectors.toList());
    }

}
