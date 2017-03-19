package club.mixr.data.repository;

import club.mixr.data.entity.RecipeIngredientEntity;

import java.util.stream.Stream;

public interface RecipeIngredientRepository extends BaseRepository<RecipeIngredientEntity, String> {

    Stream<RecipeIngredientEntity> findByRecipeId(Long id);
}