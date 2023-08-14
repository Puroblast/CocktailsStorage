package com.puroblast.cocktailsstorage.di

import android.content.Context
import androidx.room.Room
import com.puroblast.cocktailsstorage.data.local.database.CocktailsDatabase
import com.puroblast.cocktailsstorage.data.local.dao.CocktailsDao
import com.puroblast.cocktailsstorage.utils.COCKTAILS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {


    @Provides
    fun providesDao(database: CocktailsDatabase): CocktailsDao {
        return database.cocktailsDao()
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): CocktailsDatabase {
        return Room.databaseBuilder(
            context,
            CocktailsDatabase::class.java,
            COCKTAILS_DATABASE
        )
            .build()
    }


}