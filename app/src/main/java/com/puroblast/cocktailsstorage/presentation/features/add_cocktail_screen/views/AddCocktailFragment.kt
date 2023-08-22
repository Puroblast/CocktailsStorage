package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.views

import android.app.ActionBar.LayoutParams
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.databinding.FragmentAddCocktailBinding
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.AddCocktailLifecycleObserver
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.viewmodels.AddCocktailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TITLE_TEXT = "Add title"

@AndroidEntryPoint
class AddCocktailFragment : Fragment(R.layout.fragment_add_cocktail) {

    private val binding by viewBinding(FragmentAddCocktailBinding::bind)
    private lateinit var observer: AddCocktailLifecycleObserver
    private val viewModel by activityViewModels<AddCocktailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ingredients.collect {
                    binding.ingredientsChipGroup.removeViews(0,binding.ingredientsChipGroup.size-1)
                    it.forEach {
                        val chip = layoutInflater.inflate(
                            R.layout.chip_item, binding.ingredientsChipGroup, false
                        ) as Chip
                        chip.text = it.name
                        binding.ingredientsChipGroup.addView(
                            chip, binding.ingredientsChipGroup.size - 1
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(observer)
    }

    private fun initView() {
        observer = AddCocktailLifecycleObserver(requireActivity().activityResultRegistry, binding)
        lifecycle.addObserver(observer)
    }

    private fun setupListeners() {
        with(binding) {
            cocktailImageCard.setOnClickListener {
                observer.selectImage()
            }
            cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
            addChipButton.setOnClickListener {
                findNavController().navigate(R.id.action_addCocktailFragment_to_addIngredientDialogFragment)
            }
            saveButton.setOnClickListener {
                if (cocktailNameText.text.toString().isNotEmpty()) {
                    viewModel.save(
                        Cocktail(
                            title = cocktailNameText.text.toString(), ingredients = emptyList()
                        )
                    )
                    findNavController().popBackStack()
                } else {
                    val redColor = ContextCompat.getColor(requireContext(), R.color.red)
                    cocktailNameInput.error = TITLE_TEXT
                    cocktailNameText.setHintTextColor(redColor)
                    cocktailNameInput.placeholderTextColor = ColorStateList.valueOf(redColor)
                    cocktailNameInput.hintTextColor = ColorStateList.valueOf(redColor)
                }

            }
        }
    }

}
