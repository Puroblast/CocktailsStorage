package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.CocktailsViewHolder
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.EmptyCocktailsAdapterItem
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonAdapterDelegate
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

class EmptyCocktailsAdapterDelegate : CommonAdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.empty_cocktails_item,
                    parent,
                    false
                )
        return CocktailsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: CommonDelegateItem,
        position: Int
    ) {
        val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = true
        (holder as CocktailsViewHolder).bind(item)
    }

    override fun isOfViewType(item: CommonDelegateItem): Boolean = item is EmptyCocktailsAdapterItem

}