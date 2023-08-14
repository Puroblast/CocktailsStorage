package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.CocktailsAdapterItem
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.CocktailsViewHolder
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonAdapterDelegate
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

class CocktailsAdapterDelegate : CommonAdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.cocktails_item,
                    parent,
                    false
                )
        return CocktailsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        item: CommonDelegateItem,
        position: Int
    ) {
        (holder as CocktailsViewHolder).bind(item)
    }

    override fun isOfViewType(item: CommonDelegateItem): Boolean = item is CocktailsAdapterItem


}