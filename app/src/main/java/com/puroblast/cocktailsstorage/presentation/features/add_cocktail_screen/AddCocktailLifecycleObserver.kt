package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen

import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.puroblast.cocktailsstorage.databinding.FragmentAddCocktailBinding

private const val REGISTRY_KEY = "key"
private const val IMAGE_PATH = "image/*"

class AddCocktailLifecycleObserver(
    private val registry: ActivityResultRegistry,
    private val binding: FragmentAddCocktailBinding
) : DefaultLifecycleObserver {

    private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register(REGISTRY_KEY, owner, ActivityResultContracts.GetContent()) {
            binding.cocktailImage.setImageURI(it)
            binding.cocktailImage.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    fun selectImage() {
        getContent.launch(IMAGE_PATH)
    }

}
