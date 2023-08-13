package com.puroblast.cocktailsstorage.data.local.converters

import androidx.room.TypeConverter

class IngredientsConverter {

    @TypeConverter
    fun toString(descriptions : List<String>): String {
        return if (descriptions.isNotEmpty()){
            descriptions.joinToString(SEPARATOR)
        } else
            ""
    }

    @TypeConverter
    fun toList(descriptions: String): List<String> {
        return if (descriptions != ""){
            descriptions.split(SEPARATOR)
        } else  {
            emptyList()
        }
    }
    companion object {
        private const val SEPARATOR = ", "
    }
}
