package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler

import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.databinding.CocktailsItemBinding
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.CocktailsAdapterItem
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.EmptyCocktailsAdapterItem
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.MyCocktailsHeaderItem
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

class CocktailsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private fun bindCocktailItem(item: CocktailsAdapterItem) {
        val cocktailName: TextView = view.findViewById(R.id.cocktailName)
        val cocktailImage: ShapeableImageView = view.findViewById(R.id.cocktailImage)
        with(item.content() as Cocktail) {
            cocktailName.text = title
            cocktailImage.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    private fun bindEmptyCocktailItem() {
        val addButton: FloatingActionButton = view.findViewById(R.id.addCocktailButton)
        addButton.setOnClickListener {
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
