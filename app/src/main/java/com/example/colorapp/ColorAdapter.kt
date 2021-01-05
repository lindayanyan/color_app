package com.example.colorapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ColorAdapter(private val context: Context, private val colors: MutableList<ColorCode>): BaseAdapter(){

    @Override
    fun getSize(): Int {
        return colors.size
    }

    @Override
    fun getItemID(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Object? {
        return null
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val dummyTextView = TextView(context)
        dummyTextView.text = position.toString()
        return dummyTextView
    }


}