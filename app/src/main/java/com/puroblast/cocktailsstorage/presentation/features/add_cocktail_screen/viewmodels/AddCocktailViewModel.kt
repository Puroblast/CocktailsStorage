package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.data.local.storage.CocktailsStorage
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.IngredientsState
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.models.IngredientItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCocktailViewModel @Inject constructor(
    private val storage: CocktailsStorage,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _ingredientsState = MutableStateFlow(IngredientsState())
    val ingredientsState = _ingredientsState.asStateFlow()
    val ingredients = savedStateHandle.getStateFlow("Ingredients" , ingredientsState.value.ingredients)
    fun saveIngredient(ingredient : IngredientItem){
        val newIngredients = ingredients.value.toMutableList()
        newIngredients.add(ingredient)
        savedStateHandle["Ingredients"] = newIngredients
    }

    fun save(cocktail: Cocktail) {
        viewModelScope.launch {
            storage.save(cocktail)
        }
    }

}
