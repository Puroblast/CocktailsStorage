package com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapter_items

import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

class MyCocktailsHeaderItem(
    val id: Int = 1,
    private val layoutId: Int = R.layout.my_cocktails_header_item
) : CommonDelegateItem {
    override fun content(): Any = layoutId

    override fun id(): Int = id

    override fun compareToOther(other: CommonDelegateItem): Boolean {
        return (other as MyCocktailsHeaderItem).layoutId == content()
    }

}
