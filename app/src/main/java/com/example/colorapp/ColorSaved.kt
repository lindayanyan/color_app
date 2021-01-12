package com.example.colorapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colorapp.adaptor.adaptor_color
import com.example.colorapp.data.DataSource
import java.io.FileInputStream


class ColorSaved : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_saved)
        makeList()
    }

    private fun makeList(){
        val codes = load()
        val myDataset =  DataSource(codes).loadColors()
        val recyclerView = findViewById<RecyclerView>(R.id.color_list)
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adaptor_color(myDataset)
        recyclerView.setHasFixedSize(true)

    }

    private fun makeGrid(){
        var colors = mutableListOf<ColorCode>()
        //use recycler view to display colors
        var colorList = findViewById<RecyclerView>(R.id.color_list) //crash
        val colorAdapter = ColorAdapter(this, colors)
        //colorList.adapter = colorAdapter

    }
    fun load(): List<String>? {
        try {
            val fileInputStream: FileInputStream = openFileInput("outTextFile.txt")
            var read = -1
            val buffer = StringBuffer()
            while (fileInputStream.read().also { read = it } != -1) {
                buffer.append(read.toChar())
            }
            Log.d("Code", buffer.toString())
            val codes = buffer.toString().substring(1).split(",")
            return codes
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}