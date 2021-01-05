package com.example.colorapp

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ColorSaved : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_saved)
        makeGrid() //crash site
    }

    private fun makeGrid(){
        var colors = mutableListOf<ColorCode>()
        var colorGrid = findViewById<GridView>(R.id.color_grid) //crash
        val colorAdapter = ColorAdapter(this, colors)
        colorGrid.adapter = colorAdapter

        colorGrid.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            val text = findViewById<TextView>(R.id.info)
            // insert color.getText method
        }

    }
}