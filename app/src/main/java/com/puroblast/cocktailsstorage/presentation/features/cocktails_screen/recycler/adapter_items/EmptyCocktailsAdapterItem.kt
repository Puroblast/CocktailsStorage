package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items

import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

class EmptyCocktailsAdapterItem(
    val id: Int = 0,
    private val layoutId: Int = R.layout.empty_cocktails_item
) : CommonDelegateItem {

    override fun content(): Any = layoutId

    override fun id(): Int = id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as EmptyCocktailsAdapterItem).layoutId == content()
    }

}
