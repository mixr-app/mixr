package club.mixr.dto

/**
 * Created by jdifebo on 3/14/2017.
 */
data class Source(val id: Long, val name: String, val description: String)

data class SourceToCreate(val name: String="", val description: String="")