package com.puroblast.cocktailsstorage.data.local.converters

import androidx.room.TypeConverter
import com.puroblast.cocktailsstorage.utils.EMPTY_STRING
import com.puroblast.cocktailsstorage.utils.SEPARATOR

class IngredientsConverter {

    @TypeConverter
    fun toString(descriptions: List<String>): String {
        return if (descriptions.isNotEmpty()) {
            descriptions.joinToString(SEPARATOR)
        } else
            EMPTY_STRING
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
