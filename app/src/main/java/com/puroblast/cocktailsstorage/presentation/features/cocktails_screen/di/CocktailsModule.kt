package com.puroblast.cocktailsstorage.presentation.features.cocktails_screen.di

import com.puroblast.cocktailsstorage.utils.mappers.CocktailsToCocktailsItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object CocktailsModule {

    @Provides
    fun provideCocktailsToCocktailsItemMapper(): CocktailsToCocktailsItemMapper {
        return CocktailsToCocktailsItemMapper()
    }
}