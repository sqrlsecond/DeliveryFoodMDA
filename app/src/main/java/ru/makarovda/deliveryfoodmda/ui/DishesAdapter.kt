package ru.makarovda.deliveryfoodmda.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.makarovda.deliveryfoodmda.R
import ru.makarovda.deliveryfoodmda.domain.Dish

class DishesAdapter(val context: Context, var dishes: List<Dish>, val itemClickListener: (Dish) -> Unit):
    RecyclerView.Adapter<DishesAdapter.DishViewHolder>(){

    class DishViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView

        val image: ImageView

        init {
            name = itemView.findViewById(R.id.dish_textView)
            image = itemView.findViewById(R.id.dishItem_imageView)
        }
    }

    private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(
            LayoutInflater
                .from(parent.context).inflate(R.layout.dish_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: DishesAdapter.DishViewHolder, position: Int) {
        holder.name.text = dishes[position].name
        Glide
            .with(context)
            .load(dishes[position].imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .apply(requestOptions)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            itemClickListener(dishes[position])
        }
    }


}