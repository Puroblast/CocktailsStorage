package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.CocktailsState
import com.puroblast.cocktailsstorage.data.local.storage.CocktailsStorage
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.EmptyCocktailsAdapterItem
import com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.recycler.adapter_items.MyCocktailsHeaderItem
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem
import com.puroblast.cocktailsstorage.utils.mappers.CocktailsToCocktailsItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailsViewModel @Inject constructor(
    private val storage: CocktailsStorage, private val mapper: CocktailsToCocktailsItemMapper
) : ViewModel() {

    private val _cocktailsState = MutableStateFlow(CocktailsState(emptyList(), false))
    val cocktailsState = _cocktailsState.asStateFlow()

    init {
        collectData()
    }

    fun collectData() {
        viewModelScope.launch {
            val items = storage.getAll()
            val headerItemList = mutableListOf<CommonDelegateItem>(MyCocktailsHeaderItem())
            if (items.isEmpty()) {
                _cocktailsState.value =
                    _cocktailsState.value.copy(
                        items = listOf(EmptyCocktailsAdapterItem()),
                        isBottomSheetVisible = false
                    )
            } else {
                headerItemList.addAll(mapper.map(items))
                _cocktailsState.value =
                    _cocktailsState.value.copy(items = headerItemList, isBottomSheetVisible = true)
            }
        }
    }
}