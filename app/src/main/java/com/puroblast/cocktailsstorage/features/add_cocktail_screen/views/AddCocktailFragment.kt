package com.puroblast.cocktailsstorage.features.add_cocktail_screen.views

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.databinding.FragmentAddCocktailBinding
import com.puroblast.cocktailsstorage.features.add_cocktail_screen.CocktailState
import com.puroblast.cocktailsstorage.features.add_cocktail_screen.viewmodels.AddCocktailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class AddCocktailFragment : Fragment(R.layout.fragment_add_cocktail) {

    private val binding by viewBinding(FragmentAddCocktailBinding::bind)
    private val viewModel by activityViewModels<AddCocktailViewModel>()
    private lateinit var pickMedia : ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                viewModel.saveAddCocktailUi(image = it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeAddCocktailUi()

    }

    override fun onDestroy() {
        super.onDestroy()
        pickMedia.unregister()
    }

    private fun setupListeners() {
        with(binding) {
            descriptionText.doAfterTextChanged {
                viewModel.saveAddCocktailUi(description = it.toString())
            }
            recipeText.doAfterTextChanged {
                viewModel.saveAddCocktailUi(recipe = it.toString())
            }
            cocktailNameText.doAfterTextChanged {
                viewModel.saveAddCocktailUi(name = it.toString())
            }
            cocktailImageCard.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            cancelButton.setOnClickListener {
                viewModel.resetAddCocktailState()
                findNavController().popBackStack()
            }
            addChipButton.setOnClickListener {
                findNavController().navigate(R.id.action_addCocktailFragment_to_addIngredientDialogFragment)
            }
            saveButton.setOnClickListener {
                if (cocktailNameText.text.toString().isNotEmpty()) {
                    viewModel.save(
                        Cocktail(
                            title = cocktailNameText.text.toString(),
                            ingredients = emptyList()
                        )
                    )
                    findNavController().popBackStack()
                } else {
                    val redColor = ContextCompat.getColor(requireContext(), R.color.red)
                    cocktailNameInput.error = getString(R.string.title_text)
                    cocktailNameText.setHintTextColor(redColor)
                    cocktailNameInput.placeholderTextColor = ColorStateList.valueOf(redColor)
                    cocktailNameInput.hintTextColor = ColorStateList.valueOf(redColor)
                }

            }
        }
    }

    private fun observeAddCocktailUi(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cocktailState.collect {cocktailState ->
                    with(binding) {
                        if (ingredientsChipGroup.size - 1 != cocktailState.ingredients.size) {
                            ingredientsChipGroup.removeViews(
                                0,
                                ingredientsChipGroup.size - 1
                            )
                            cocktailState.ingredients.forEach {name ->
                                val chip = layoutInflater.inflate(
                                    R.layout.chip_item,
                                    ingredientsChipGroup,
                                    false
                                ) as Chip
                                chip.text = name
                                chip.setOnCloseIconClickListener {
                                    val index = ingredientsChipGroup.indexOfChild(it)
                                    viewModel.deleteClosedIngredient(index)
                                }
                                ingredientsChipGroup.addView(
                                    chip,
                                    ingredientsChipGroup.size - 1
                                )
                            }
                        }
                        if (cocktailState.image != null) {
                            cocktailImage.setImageURI(cocktailState.image)
                            cocktailImage.scaleType = ImageView.ScaleType.CENTER_CROP
                        }
                    }
                }
            }
        }
    }

}
