package com.puroblast.cocktailsstorage.data.local.storage

import com.puroblast.cocktailsstorage.data.local.dao.CocktailsDao
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailsStorage @Inject constructor(private val dao: CocktailsDao) {

    suspend fun getAll(): List<Cocktail> = withContext(Dispatchers.IO) {
        dao.getAll()
    }

    suspend fun save(cocktail: Cocktail) = withContext(Dispatchers.IO) {
        dao.insert(cocktail)
    }

}