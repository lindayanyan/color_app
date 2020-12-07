package com.example.colorapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.flag.BubbleFlag
import com.skydoves.colorpickerview.flag.FlagMode
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener


class MainActivity : AppCompatActivity() {

    private val colorPickerView: ColorPickerView? = findViewById(R.id.colorPickerView)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        colorPickerView?.setHsvPaletteDrawable()
        //Log.d(TAG, "FILE2")

        //colorPickerView = findViewById(R.id.colorPickerView)
        val bubbleFlag = BubbleFlag(this)
        bubbleFlag.flagMode = FlagMode.FADE

        if (colorPickerView != null) {
            colorPickerView.setFlagView(bubbleFlag)
            colorPickerView.setColorListener(
                ColorEnvelopeListener { envelope: ColorEnvelope, fromUser: Boolean ->
                    //Timber.d("color: %s", envelope.hexCode)
                   // setLayoutColor(envelope)
                })
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    //code below is adapted from https://developer.android.com/training/appbar/actions
   override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_menu -> {
            val toast = Toast.makeText(applicationContext, "Test", Toast.LENGTH_SHORT)
            toast.show()
            true
        }

        R.id.action_test -> {
            val toast = Toast.makeText(applicationContext, "Test", Toast.LENGTH_SHORT)
            toast.show()
            true
        }


       else -> {
           // If we got here, the user's action was not recognized.
           // Invoke the superclass to handle it.
           super.onOptionsItemSelected(item)
       }
   }

    /*private fun setLayoutColor(envelope: ColorEnvelope) {
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "#" + envelope.hexCode
        val alphaTileView = findViewById<AlphaTileView>(R.id.alphaTileView)
        alphaTileView.setPaintColor(envelope.color)
    }*/


}