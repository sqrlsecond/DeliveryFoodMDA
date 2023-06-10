package ru.makarovda.deliveryfoodmda.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.makarovda.deliveryfoodmda.R
import ru.makarovda.deliveryfoodmda.domain.Dish
import ru.makarovda.deliveryfoodmda.domain.RequestState

class DishesFragment: Fragment(R.layout.fragment_dishes) {

    private val dishesVM: DishesViewModel by viewModels { DishesViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DishesAdapter(requireContext(), ArrayList<Dish>()) {
            val dishDialog = DishDialogFragment()
            val dishBundle = Bundle().apply {
                putString(DishDialogFragment.DISH_NAME, it.name)
                putString(DishDialogFragment.DISH_DESC, it.description)
                putString(DishDialogFragment.DISH_IMG_URL, it.imageUrl)
                putInt(DishDialogFragment.DISH_WEIGHT, it.weight)
                putInt(DishDialogFragment.DISH_PRICE, it.price)
            }
            dishDialog.arguments = dishBundle
            dishDialog.show(childFragmentManager, null)
        }

        val recView = view.findViewById<RecyclerView>(R.id.dishes_recView)
        recView.adapter = adapter
        recView.layoutManager = GridLayoutManager(requireActivity(), 3)

        val tegsRecView = view.findViewById<RecyclerView>(R.id.tags_recView)
        val tegsAdapter = TegsAdapter(dishesVM.dishesTegs.toList())
        tegsRecView.adapter = tegsAdapter
        tegsRecView.layoutManager = LinearLayoutManager(
            requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch(Dispatchers.Main){
            dishesVM.dishesFlow.collect {
                if (it is RequestState.DishesSuccess) {
                    adapter.dishes = it.dishes
                    adapter.notifyDataSetChanged()
                    tegsAdapter.tegs = dishesVM.dishesTegs.toList()
                    tegsAdapter.notifyDataSetChanged()
                }
                else if(it is RequestState.Error) {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dishesVM.requestCategories()
    }
}