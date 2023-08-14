package com.puroblast.cocktailsstorage.utils.adapter_delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CommonAdapter :
    ListAdapter<CommonDelegateItem, RecyclerView.ViewHolder>(CocktailsDiffUtilCallback) {
    private val delegates: MutableList<CommonAdapterDelegate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position), position)
    }

    fun addDelegate(delegate: CommonAdapterDelegate) {
        delegates.add(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { it.isOfViewType(currentList[position]) }
    }

    object CocktailsDiffUtilCallback : DiffUtil.ItemCallback<CommonDelegateItem>() {
        override fun areItemsTheSame(
            oldItem: CommonDelegateItem, newItem: CommonDelegateItem
        ): Boolean = oldItem::class == newItem::class && oldItem.id() == newItem.id()

        override fun areContentsTheSame(
            oldItem: CommonDelegateItem, newItem: CommonDelegateItem
        ): Boolean = oldItem.compareToOther(newItem)

        override fun getChangePayload(
            oldItem: CommonDelegateItem, newItem: CommonDelegateItem
        ): Any? {
            if (areContentsTheSame(oldItem, newItem)) return newItem
            return super.getChangePayload(oldItem, newItem)
        }
    }
}