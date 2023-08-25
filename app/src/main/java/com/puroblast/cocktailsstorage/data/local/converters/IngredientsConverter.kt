package com.puroblast.cocktailsstorage.data.local.converters

import androidx.room.TypeConverter

private const val SEPARATOR = ", "
private const val EMPTY_STRING = ""

class IngredientsConverter {

    @TypeConverter
    fun toString(descriptions: List<String>): String {
        return if (descriptions.isNotEmpty()) {
            descriptions.joinToString(SEPARATOR)
        } else EMPTY_STRING
    }

    @TypeConverter
    fun toList(descriptions: String): List<String> {
        return if (descriptions.isNotBlank()) {
            descriptions.split(SEPARATOR)
        } else {
            emptyList()
        }
    }

}
