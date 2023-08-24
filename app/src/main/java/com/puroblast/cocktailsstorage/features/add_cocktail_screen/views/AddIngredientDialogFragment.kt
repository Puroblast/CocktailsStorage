package com.puroblast.cocktailsstorage.features.add_cocktail_screen.views

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.databinding.FragmentAddIngredientDialogBinding
import com.puroblast.cocktailsstorage.features.add_cocktail_screen.viewmodels.AddCocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIngredientDialogFragment : DialogFragment(R.layout.fragment_add_ingredient_dialog) {

    private val binding by viewBinding(FragmentAddIngredientDialogBinding::bind)
    private val viewModel by activityViewModels<AddCocktailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(R.style.dialog_custom, R.style.dialog_custom)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        binding.ingredientNameText.doAfterTextChanged {
            outState.putString("Ingredient", binding.ingredientNameText.text.toString())
        }
    }

    private fun setupListeners() {
        with(binding) {
            cancelButton.setOnClickListener {
                dismiss()
            }
            addButton.setOnClickListener {
                if (ingredientNameText.text.isNullOrBlank()) {
                    with(ingredientNameInput) {
                        val redColor = ContextCompat.getColor(requireContext(), R.color.red)
                        error = getString(R.string.title_text)
                        placeholderTextColor = ColorStateList.valueOf(redColor)
                        hintTextColor = ColorStateList.valueOf(redColor)
                    }
                } else {
                    viewModel.saveAddCocktailUi(ingredient = ingredientNameText.text.toString())
                    dismiss()
                }
            }
        }
    }

}
