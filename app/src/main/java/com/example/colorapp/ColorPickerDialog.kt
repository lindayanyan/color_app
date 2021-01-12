/*
 * Designed and developed by 2017 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//code referenced from https://github.com/skydoves/ColorPickerView#palette

package com.example.colorapp

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.colorpickerview.AlphaTileView
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.flag.BubbleFlag
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar
import java.io.IOException
import java.io.OutputStreamWriter


/**
 * ColorPickerDialog is a dialog what having [ColorPickerView], [AlphaSlideBar] and
 * [BrightnessSlideBar].
 */
class ColorPickerDialog: AppCompatActivity() {

    lateinit var color_save: MutableList<ColorCode>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker_dialog2)
        dialog()

        val btn = findViewById<Button>(R.id.button_dialog) // set on-click listener
        btn.setOnClickListener {
            dialog()
        }

    }


    private fun dialog() {

        val builder = ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("Test")
                //refers to the two buttons on page, cancel should do nothing while select should save the color and display codes
                .setPositiveButton(
                        "select", //updates alphaTile to newly selected color
                        ColorEnvelopeListener { envelope: ColorEnvelope?, fromUser: Boolean ->
                            if (envelope != null) {
                                setLayoutColor(envelope)
                                //color envelope is a is a wrapper class of color models for providing more variety of color models.
                            }
                        })
                .setNegativeButton(
                        "cancel") //no update to alphaTile
                { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
        builder.colorPickerView.setFlagView(BubbleFlag(this))
        builder.show()
    }

    private fun setLayoutColor(envelope: ColorEnvelope) {

        val color = ColorCode(envelope.hexCode.substring(2)) //creates ColorCode object
        val textView_cc1 = findViewById<TextView>(R.id.cc1)//finds textview
        val textView_cc2 = findViewById<TextView>(R.id.cc2)
        val textView_cc3 = findViewById<TextView>(R.id.cc3)

        val cg = color_group(color) //creates color group
        color_save = mutableListOf<ColorCode>(cg.get_c1(), cg.get_c2(), cg.get_c3())

        //original chosen color
        val alphaTileView1 = findViewById<AlphaTileView>(R.id.alphaTileView_1)//updates alphatile
        alphaTileView1.setPaintColor(envelope.color)
        textView_cc1.text = color.get_text()
        val b1 = findViewById<Button>(R.id.save1_button)
        b1.setOnClickListener {
            save(0)

        }

        //color #2
        val color2 = cg.get_c2()
        val n2 = Color.parseColor("#"+color2.get_hex()) // goes from hex to int of color*/
        val alphaTileView2 = findViewById<AlphaTileView>(R.id.alphaTileView_2)
        alphaTileView2.setPaintColor(n2) //crash
        textView_cc2.text = color2.get_text()
        val b2 = findViewById<Button>(R.id.save2_button)
        b2.setOnClickListener {
            save(1)

        }

        //color #3
        val color3 = cg.get_c3()
        val n3 = Color.parseColor("#"+color3.get_hex())
        val alphaTileView3 = findViewById<AlphaTileView>(R.id.alphaTileView_3)
        alphaTileView3.setPaintColor(n3)
        textView_cc3.text = color3.get_text()
        val b3 = findViewById<Button>(R.id.save3_button)
        b3.setOnClickListener {
            save(2)
        }
    }

    // adapted from https://abhiandroid.com/database/internal-storage
    //originally in Java
    private fun save(num: Int) // SAVE; writes to internal .txt file
    {
       var myContext = getApplicationContext()
       val outputStreamWriter = OutputStreamWriter(myContext.openFileOutput("outTextFile.txt", Context.MODE_APPEND))
        try {
            outputStreamWriter.append(","+color_save[num].get_hex())//works w .write
            outputStreamWriter.close()
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }
    }


}