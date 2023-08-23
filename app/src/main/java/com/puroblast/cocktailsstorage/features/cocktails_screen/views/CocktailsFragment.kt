package com.puroblast.cocktailsstorage.features.cocktails_screen.views

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.cocktailsstorage.R
import com.puroblast.cocktailsstorage.databinding.FragmentCocktailsBinding
import com.puroblast.cocktailsstorage.features.cocktails_screen.CocktailsUiState
import com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapters.CocktailsAdapterDelegate
import com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapters.EmptyCocktailsAdapterDelegate
import com.puroblast.cocktailsstorage.features.cocktails_screen.recycler.adapters.HeaderAdapterDelegate
import com.puroblast.cocktailsstorage.features.cocktails_screen.viewmodels.CocktailsViewModel
import com.puroblast.cocktailsstorage.utils.adapter_delegate.CommonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private val binding by viewBinding(FragmentCocktailsBinding::bind)
    private val viewModel by viewModels<CocktailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val cocktailsLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        val cocktailsAdapter = CommonAdapter().apply {
            addDelegate(CocktailsAdapterDelegate())
            addDelegate(EmptyCocktailsAdapterDelegate())
            addDelegate(HeaderAdapterDelegate())
        }
        with(binding) {
            cocktailsRecycler.layoutManager = cocktailsLayoutManager
            cocktailsRecycler.adapter = cocktailsAdapter
            addButton.setOnClickListener {
                findNavController().navigate(R.id.action_cocktailsFragment_to_addCocktailFragment2)
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.collectData()
                viewModel.cocktailsUiState.collect {
                    render(it,cocktailsAdapter)
                }
            }
        }
    }

    private fun render(uiState: CocktailsUiState, adapter: CommonAdapter) {
        adapter.submitList(uiState.items)
        binding.addButton.isVisible = uiState.isBottomSheetVisible
    }

}
