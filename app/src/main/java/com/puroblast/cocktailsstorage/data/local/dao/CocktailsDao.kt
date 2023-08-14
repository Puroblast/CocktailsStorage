package com.puroblast.cocktailsstorage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail

@Dao
interface CocktailsDao {

    @Query("SELECT * FROM Cocktail")
    suspend fun getAll(): List<Cocktail>

    @Insert(onConflict = REPLACE)
    suspend fun insert(cocktail: Cocktail)

}