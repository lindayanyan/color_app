package com.example.colorapp.adaptor

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.colorapp.ColorCode
import com.example.colorapp.R
import com.skydoves.colorpickerview.AlphaTileView
//code referenced from https://developer.android.com/codelabs/basic-android-kotlin-training-affirmations-app#0

class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.info)
    val imageView: AlphaTileView  = view.findViewById(R.id.tile)
}

class adaptor_color(
        private val dataset: List<ColorCode>
) : RecyclerView.Adapter<adaptor_color.ItemViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an color object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.info)
        val tileView: AlphaTileView = view.findViewById(R.id.tile)

    }
    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptor_color.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.colorview, parent, false)

        return ItemViewHolder(adapterLayout)

    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.tileView.setPaintColor(Color.parseColor("#"+item.get_hex()))
        holder.textView.setText(item.get_text())
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size

}

