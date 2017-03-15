package club.mixr.dto

/**
 * Created by jdifebo on 3/14/2017.
 */
data class Ingredient(val id: Long, val name: String, val description: String)

data class IngredientToCreate(val name: String="", val description: String="")