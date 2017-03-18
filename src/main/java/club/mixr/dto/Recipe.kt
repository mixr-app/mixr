package club.mixr.dto

import club.mixr.data.entity.IngredientType

/**
 * Created by jdifebo on 3/14/2017.
 */
data class Recipe(val id: Long, val name: String, val description: String, val instructions: String, val imageLocation: String?, val sourceId: Long)