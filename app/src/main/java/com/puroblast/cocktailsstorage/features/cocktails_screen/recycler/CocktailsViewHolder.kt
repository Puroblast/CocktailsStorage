package com.puroblast.cocktailsstorage.features.cocktails_screen.recycler

import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.databinding.CocktailsItemBinding
import com.puroblast.cocktailsstorage.databinding.EmptyCocktailsItemBinding
import com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapter_items.CocktailsAdapterItem
import com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapter_items.EmptyCocktailsAdapterItem
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

class CocktailsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val cocktailItemBinding by viewBinding(CocktailsItemBinding::bind)
    private val emptyCocktailItemBinding by viewBinding(EmptyCocktailsItemBinding::bind)

    private fun bindCocktailItem(item: CocktailsAdapterItem) {
        with(item.content() as Cocktail) {
            cocktailItemBinding.cocktailName.text = title
            cocktailItemBinding.cocktailImage.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    private fun bindEmptyCocktailItem() {
        emptyCocktailItemBinding.addCocktailButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_cocktailsFragment_to_addCocktailFragment2)
        }
    }

    fun bind(item: CommonDelegateItem) {
        when (item) {
            is CocktailsAdapterItem -> bindCocktailItem(item)
            is EmptyCocktailsAdapterItem -> bindEmptyCocktailItem()
        }
    }

}
