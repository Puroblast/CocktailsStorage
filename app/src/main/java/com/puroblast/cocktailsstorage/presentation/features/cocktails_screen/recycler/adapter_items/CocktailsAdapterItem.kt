package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items

import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem


class CocktailsAdapterItem(val id: Int, private val value: Cocktail) : CommonDelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as CocktailsAdapterItem).value == content()
    }
}