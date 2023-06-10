package ru.makarovda.deliveryfoodmda.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.makarovda.deliveryfoodmda.R
import ru.makarovda.deliveryfoodmda.domain.FoodCategory
import ru.makarovda.deliveryfoodmda.domain.RequestState

class CategoriesFragment: Fragment(R.layout.fragment_categories) {

    private val categoriesVM: FoodCategoriesViewModel by viewModels { FoodCategoriesViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FoodCategoriesAdapter(requireContext(), ArrayList<FoodCategory>())

        val recView = view.findViewById<RecyclerView>(R.id.categories_recView)
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireActivity())

        lifecycleScope.launch(Dispatchers.Main){
            categoriesVM.categoriesFlow.collect {
                if (it is RequestState.FoodCategoriesSuccess) {
                    adapter.categories = it.categories
                    adapter.notifyDataSetChanged()
                }
                else if(it is RequestState.Error) {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        categoriesVM.requestCategories()
    }
}