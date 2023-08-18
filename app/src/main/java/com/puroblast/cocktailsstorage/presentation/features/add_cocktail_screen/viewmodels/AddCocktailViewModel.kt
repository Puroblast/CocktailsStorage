package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.data.local.storage.CocktailsStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCocktailViewModel @Inject constructor(
    private val storage: CocktailsStorage
) : ViewModel() {

    fun save(cocktail: Cocktail) {
        viewModelScope.launch {
            storage.save(cocktail)
        }
    }

}
