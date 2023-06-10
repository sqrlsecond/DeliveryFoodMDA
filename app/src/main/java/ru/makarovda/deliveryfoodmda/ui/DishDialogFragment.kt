package ru.makarovda.deliveryfoodmda.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.makarovda.deliveryfoodmda.R

class DishDialogFragment: DialogFragment(R.layout.dialog_dish) {

    private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Название
        view.findViewById<TextView>(R.id.dishName_textView)?.text = arguments?.getString(DISH_NAME)
        //Описание
        view.findViewById<TextView>(R.id.dishDecs_textView)?.text = arguments?.getString(DISH_DESC)
        //Цена
        view.findViewById<TextView>(R.id.dishPrice_textView)?.text = arguments?.getInt(DISH_PRICE).toString()
        //Вес
        view.findViewById<TextView>(R.id.dishWeight_textView)?.text = arguments?.getInt(DISH_WEIGHT).toString()

        val imgUrl = arguments?.getString(DISH_IMG_URL)

        val imgView = view.findViewById<ImageView>(R.id.dialogDish_imageView)

        if(imgUrl != null){
            Glide
                .with(requireContext())
                .load(imgUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .apply(requestOptions)
                .into(imgView)
        }
    }

    companion object {

        const val DISH_NAME = "DISH_NAME"

        const val DISH_DESC = "DISH_DESC"

        const val DISH_PRICE = "DISH_PRICE"

        const val DISH_WEIGHT = "DISH_WEIGHT"

        const val DISH_IMG_URL = "DISH_IMG_URL"

    }
}