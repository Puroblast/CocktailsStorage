package com.puroblast.cocktailsstorage.features.add_cocktail_screen.viewmodels

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.data.local.storage.CocktailsStorage
import com.puroblast.cocktailsstorage.features.add_cocktail_screen.CocktailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCocktailViewModel @Inject constructor(
    private val storage: CocktailsStorage,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val cocktailState = savedStateHandle.getStateFlow("Cocktail", CocktailState())

    fun saveAddCocktailUi(
        name: String = cocktailState.value.name,
        description: String? = cocktailState.value.description,
        recipe: String? = cocktailState.value.recipe,
        image: Uri? = cocktailState.value.image,
        ingredient: String = ""
    ) {
        cocktailState.value.apply {
            if (ingredient.isNotBlank()) {
                val newIngredients = ingredients.toMutableList()
                newIngredients.add(ingredient)
                savedStateHandle["Cocktail"] = copy(
                    ingredients = newIngredients,
                    name = name,
                    description = description,
                    recipe = recipe,
                    image = image
                )
            } else {
                savedStateHandle["Cocktail"] = copy(
                    name = name,
                    description = description,
                    recipe = recipe,
                    image = image
                )
            }
        }
    }

    fun deleteClosedIngredient(index : Int){
        with(cocktailState.value){
            val changedIngredients = ingredients.toMutableList()
            changedIngredients.removeAt(index)
            savedStateHandle["Cocktail"] = copy(ingredients = changedIngredients)
        }
    }

    fun resetAddCocktailState() {
        savedStateHandle["Cocktail"] = CocktailState()
    }
    fun save(cocktail: Cocktail) {
        viewModelScope.launch {
            storage.save(cocktail)
        }
    }

}
