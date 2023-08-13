package com.puroblast.cocktailsstorage.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cocktail(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val ingredients : List<String>,
    val image : String? = null,
    val description : String? = null,
    val recipe : String? = null
)
