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

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.flag.BubbleFlag
import com.skydoves.colorpickerview.flag.FlagMode
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar


/**
 * ColorPickerDialog is a dialog what having [ColorPickerView], [AlphaSlideBar] and
 * [BrightnessSlideBar].
 */
class ColorPickerDialog: AppCompatActivity() {
    //private var colorListener: ColorPickerViewListener? = null
    private var colorPickerView: ColorPickerView? = null
    private val TAG = photo.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker_dialog2)

        colorPickerView?.setHsvPaletteDrawable()
        Log.d(TAG, "FILE2")

        colorPickerView = findViewById(R.id.colorPickerView)
        val bubbleFlag = BubbleFlag(this)
        bubbleFlag.flagMode = FlagMode.FADE

    //   colorPickerView.setFlagView(bubbleFlag)
     //   colorPickerView.setColorListener(
            ColorEnvelopeListener { envelope: ColorEnvelope, fromUser: Boolean ->
                //Timber.d("color: %s", envelope.hexCode)
                setLayoutColor(envelope)
            }
    }

    @SuppressLint("SetTextI18n")
    private fun setLayoutColor(envelope: ColorEnvelope) {
        //val textView = findViewById<TextView>(R.id.textView)
        //textView.text = "#" + envelope.hexCode
       // val alphaTileView = findViewById<AlphaTileView>(R.id.alphaTileView)
        //alphaTileView.setPaintColor(envelope.color)
    }


}