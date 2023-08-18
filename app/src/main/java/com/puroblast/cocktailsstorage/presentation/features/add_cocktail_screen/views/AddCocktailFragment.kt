package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.views

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.data.local.entities.Cocktail
import com.puroblast.cocktailsstorage.databinding.FragmentAddCocktailBinding
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.AddCocktailLifecycleObserver
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.viewmodels.AddCocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TITLE_TEXT = "Add title"

@AndroidEntryPoint
class AddCocktailFragment : Fragment(R.layout.fragment_add_cocktail) {

    private val binding by viewBinding(FragmentAddCocktailBinding::bind)
    private lateinit var observer: AddCocktailLifecycleObserver
    private val viewModel by viewModels<AddCocktailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(observer)
    }

    private fun initView() {
        observer = AddCocktailLifecycleObserver(requireActivity().activityResultRegistry, binding)
        lifecycle.addObserver(observer)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            cocktailImageCard.setOnClickListener {
                observer.selectImage()
            }
            cancelButton.setOnClickListener {
                findNavController().popBackStack()
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
