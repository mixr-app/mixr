package club.mixr.data.repository;

import club.mixr.data.entity.RecipeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface RecipeRepository extends BaseRepository<RecipeEntity, Long> {

    @Query(nativeQuery = true, value = "select * from recipes where id in (select recipe_id from recipe_ingredients where ingredient_id in (:ingredientIds))")
    Stream<RecipeEntity> findAllRecipesByIngredientIds(@Param("ingredientIds") List<Long> ingredientIds);
}