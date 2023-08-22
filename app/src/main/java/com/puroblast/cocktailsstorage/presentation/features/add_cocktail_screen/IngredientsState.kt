package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen

import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.models.IngredientItem

data class IngredientsState(
    val ingredients : List<IngredientItem> = emptyList()
)