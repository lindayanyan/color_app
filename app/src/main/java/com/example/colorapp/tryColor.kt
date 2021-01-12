package com.example.colorapp

import android.os.Bundle
import android.service.quicksettings.Tile
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.colorpickerview.AlphaTileView
import java.io.FileInputStream

//temporarily being used to test out functions to be implemented in the saved colors screen
//specifically saving and retrieving and using strings
class tryColor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_try_color)

        val txt = findViewById<TextView>(R.id.test)
        val button = findViewById<Button>(R.id.button)
        val testTile = findViewById<AlphaTileView>(R.id.tile_save)

        button.setOnClickListener{
            val hex =load()
            txt.text = hex
            //val color = ColorCode(hex)
           // txt.text = color.get_text()
           // val color_int = Color.parseColor("#"+hex)
           // testTile.setPaintColor(color_int)
        }

    }

    //code referenced from https://abhiandroid.com/database/internal-storage
    //originally in java
    private fun load(): String {
        try {
            val fileInputStream: FileInputStream = openFileInput("TextFile.txt")
            var read = -1
            val buffer = StringBuffer()
            while (fileInputStream.read().also { read = it } != -1) {
                buffer.append(read.toChar())
            }
            Log.d("Code", buffer.toString())
            return buffer.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show()
        return ""

    }

    private fun setTile(tile: Tile){

    }
}