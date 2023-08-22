package com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.views


import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.databinding.FragmentAddIngredientDialogBinding
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.models.IngredientItem
import com.puroblast.cocktailsstorage.presentation.features.add_cocktail_screen.viewmodels.AddCocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIngredientDialogFragment : DialogFragment(R.layout.fragment_add_ingredient_dialog) {

    private val binding by viewBinding(FragmentAddIngredientDialogBinding::bind)
    private val viewModel by activityViewModels<AddCocktailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            binding.ingredientNameText.setText(savedInstanceState.getString("Ingredient"))
        }

        binding.addButton.setOnClickListener {
            viewModel.saveIngredient(IngredientItem(name = binding.ingredientNameText.text.toString()))
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.ingredientNameText.doAfterTextChanged {
            outState.putString("Ingredient" , binding.ingredientNameText.text.toString())
        }
    }
}