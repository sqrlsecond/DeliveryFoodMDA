package ru.makarovda.deliveryfoodmda.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.makarovda.deliveryfoodmda.R
import ru.makarovda.deliveryfoodmda.domain.FoodCategory

class FoodCategoriesAdapter(val context: Context, var categories: List<FoodCategory>):
    RecyclerView.Adapter<FoodCategoriesAdapter.FoodCategoryViewHolder>(){

        class FoodCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val name: TextView

            val image: ImageView

            init {
                name = itemView.findViewById(R.id.foodCategoryName_textView)
                image = itemView.findViewById(R.id.foodCategory_imageView)
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        return FoodCategoryViewHolder(
            LayoutInflater
                .from(parent.context).inflate(R.layout.food_category_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        holder.name.text = categories[position].name
        Glide
            .with(context)
            .load(categories[position].imageUrl)
            .into(holder.image)
    }
}