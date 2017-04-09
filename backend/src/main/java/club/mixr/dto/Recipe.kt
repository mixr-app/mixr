package club.mixr.dto

import club.mixr.data.entity.IngredientType

/**
 * Created by jdifebo on 3/14/2017.
 */
//data class Recipe(val id: Long, val name: String, val description: String, val instructions: String, val imageLocation: String?, val sourceId: Long)

data class Recipe(val id: Long,
                                 val name: String,
                                 val description: String,
                                 val instructions: String,
                                 val imageLocation: String?,
                                 val sourceId: Long,
                                 var ingredients: List<RecipeIngredient> )

data class RecipeIngredient(val id: Long, val recipeId: Long, val ingredientId: Long, val amount: Double, val unit: String)

data class RecipeToCreate(val name: String = "",
                          val description: String = "",
                          val instructions: String = "",
                          val imageLocation: String = "",
                          val sourceId: Long = 1,
                          val ingredients: List<IngredientsToCreate> = listOf() )

data class IngredientsToCreate(   val amount: Double=0.0,
                                  val unit: String="",
                                  val ingredientId: Long=-1 )