package ru.makarovda.deliveryfoodmda.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.makarovda.deliveryfoodmda.R

class TegsAdapter(var tegs: List<String>):
    RecyclerView.Adapter<TegsAdapter.TegViewHolder>(){

        class TegViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val teg: TextView

            init {
                teg = itemView.findViewById(R.id.tegName_textView)
            }
        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TegViewHolder {
        return TegViewHolder(
            LayoutInflater
                .from(parent.context).inflate(R.layout.teg_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tegs.size
    }

    override fun onBindViewHolder(holder: TegViewHolder, position: Int) {
        holder.teg.text = tegs[position]
    }
}