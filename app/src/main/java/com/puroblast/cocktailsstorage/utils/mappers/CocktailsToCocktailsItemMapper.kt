package com.puroblast.cocktailsstorage.utils.mappers

import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapter_items.CocktailsAdapterItem
import javax.inject.Inject

class CocktailsToCocktailsItemMapper @Inject constructor() {

    fun map(cocktails: List<Cocktail>): List<CocktailsAdapterItem> {
        return cocktails.map {
            CocktailsAdapterItem(id = it.id, value = it)
        }
    }

}
