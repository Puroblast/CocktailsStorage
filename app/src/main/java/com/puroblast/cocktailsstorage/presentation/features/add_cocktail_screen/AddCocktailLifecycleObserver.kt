package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen

import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.puroblast.cocktailsstorage.databinding.FragmentAddCocktailBinding
import com.puroblast.cocktailsstorage.utils.IMAGE_PATH
import com.puroblast.cocktailsstorage.utils.REGISTRY_KEY

class AddCocktailLifecycleObserver(
    private val registry: ActivityResultRegistry, private val binding: FragmentAddCocktailBinding
) : DefaultLifecycleObserver {

    private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register(REGISTRY_KEY, owner, ActivityResultContracts.GetContent()) {
            binding.ImageCocktail.setImageURI(it)
            binding.ImageCocktail.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    fun selectImage() {
        getContent.launch(IMAGE_PATH)
    }
}