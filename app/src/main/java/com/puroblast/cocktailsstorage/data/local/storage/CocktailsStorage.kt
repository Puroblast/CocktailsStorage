package com.puroblast.cocktailsstorage.data.local.storage

import com.puroblast.cocktailsstorage.data.local.dao.CocktailsDao
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class CocktailsStorage @Inject constructor(
    private val dao: CocktailsDao
) {

    suspend fun getAll(): List<Cocktail> = dao.getAll()

    suspend fun save(cocktail: Cocktail) = dao.insert(cocktail)

}
