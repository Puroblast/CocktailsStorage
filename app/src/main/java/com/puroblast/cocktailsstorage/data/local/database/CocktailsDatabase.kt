package com.puroblast.cocktailsstorage.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.puroblast.cocktailsstorage.data.local.converters.IngredientsConverter
import com.puroblast.cocktailsstorage.data.local.dao.CocktailsDao
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail

@Database(entities = [Cocktail::class], version = 1)
@TypeConverters(IngredientsConverter::class)
abstract class CocktailsDatabase : RoomDatabase() {

    abstract fun cocktailsDao() : CocktailsDao

}