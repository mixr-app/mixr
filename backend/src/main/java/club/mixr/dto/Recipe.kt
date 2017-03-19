package club.mixr.dto

import club.mixr.data.entity.IngredientType

/**
 * Created by jdifebo on 3/14/2017.
 */
data class Recipe(val id: Long, val name: String, val description: String, val instructions: String, val imageLocation: String?, val sourceId: Long)

data class RecipeWithIngredients(val id: Long,
                                 val name: String,
                                 val description: String,
                                 val instructions: String,
                                 val imageLocation: String?,
                                 val sourceId: Long,
                                 val ingredients: List<RecipeIngredient> )

data class RecipeIngredient(val id: Long, val recipeId: Long, val ingredient: Ingredient, val amount: Double, val unit: String)