package com.puroblast.cocktailsstorage.features.add_cocktail_screen

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CocktailState(
    val image: Uri? = null,
    val name: String = "",
    val description: String? = null,
    val recipe: String? = null,
    val ingredients: List<String> = emptyList()
) : Parcelable