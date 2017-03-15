package club.mixr.service;

import club.mixr.data.entity.IngredientEntity;
import club.mixr.data.repository.IngredientRepository;
import club.mixr.dto.Ingredient;
import club.mixr.dto.IngredientToCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jdifebo on 3/14/2017.
 */
@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> allIngredients() {
        return ingredientRepository.findAll()
                .map(IngredientEntity::toIngredient)
                .collect(Collectors.toList());
    }

    public Ingredient createIngredient(IngredientToCreate ingredientToCreate) {
        IngredientEntity persisted = new IngredientEntity(ingredientToCreate.getName(), ingredientToCreate.getDescription());
        return ingredientRepository.save(persisted).toIngredient();
    }
}
