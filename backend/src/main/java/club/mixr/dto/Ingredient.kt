package club.mixr.dto

import club.mixr.data.entity.IngredientType

/**
 * Created by jdifebo on 3/14/2017.
 */
data class Ingredient(val id: Long, val name: String, val description: String, val ingredientType: IngredientType)

data class IngredientToCreate(val name: String="", val description: String="", val ingredientType: IngredientType=IngredientType.LIQUOR)