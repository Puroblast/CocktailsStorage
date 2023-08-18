package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen

import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonDelegateItem

data class CocktailsUiState(
    val items: List<CommonDelegateItem>,
    val isBottomSheetVisible: Boolean
)
