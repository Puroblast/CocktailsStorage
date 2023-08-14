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
import com.puroblast.cocktailsstorage.utils.TITLE_TEXT
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddCocktailFragment : Fragment(R.layout.fragment_add_cocktail) {

    private val binding by viewBinding(FragmentAddCocktailBinding::bind)
    private lateinit var observer: AddCocktailLifecycleObserver
    private val viewmodel by viewModels<AddCocktailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer = AddCocktailLifecycleObserver(requireActivity().activityResultRegistry, binding)
        lifecycle.addObserver(observer)

        with(binding) {
            ImageCocktailCV.setOnClickListener {
                observer.selectImage()
            }
            cancelBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            saveBtn.setOnClickListener {
                if (cocktailNameET.text.toString().isNotEmpty()) {
                    viewmodel.save(
                        Cocktail(
                            title = cocktailNameET.text.toString(),
                            ingredients = emptyList()
                        )
                    )
                    findNavController().popBackStack()
                } else {
                    val redColor = ContextCompat.getColor(requireContext(), R.color.red)
                    cocktailNameTIL.error = TITLE_TEXT
                    cocktailNameET.setHintTextColor(redColor)
                    cocktailNameTIL.placeholderTextColor = ColorStateList.valueOf(redColor)
                    cocktailNameTIL.hintTextColor = ColorStateList.valueOf(redColor)
                }

            }
        }
    }

}