package com.puroblast.cocktailsstorage.utils.adapter_delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface CommonAdapterDelegate {

    fun onCreateViewHolder(parent: ViewGroup): ViewHolder

    fun onBindViewHolder(holder: ViewHolder, item: CommonDelegateItem, position: Int)

    fun isOfViewType(item: CommonDelegateItem): Boolean

}
