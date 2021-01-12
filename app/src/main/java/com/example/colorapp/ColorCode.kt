package com.example.colorapp

import kotlin.math.abs
import kotlin.math.roundToInt

class ColorCode() {
//algorithims referencd from https://en.wikipedia.org/wiki/HSL_and_HSV#From_RGB

    lateinit var rgb: MutableList<Int>
    lateinit var hsl: MutableList<Int>
    lateinit var cmyk: MutableList<Int>
    lateinit var hsv: MutableList<Int>
    lateinit var hex: String //no "#"


    constructor(hex_i: String) : this() {
        this.hex= hex_i

        hex2rgb(hex)
        rgb2hsl()
        rgb2cmyk()
        rgb2hsv()
    }
    constructor(hsv_i: MutableList<Int>) : this() {
        hsv = mutableListOf(hsv_i[0], hsv_i[1], hsv_i[2])

        hsv2rgb()
        rgb2cmyk()
        rgb2hex()
        rgb2hsl()
    }



    fun get_text(): String{
        val rgb_t = "RGB: " +rgb[0] +", " +rgb[1] + ", "+ rgb[2]
        val hex_t = "HEX: #" + hex
        val cmyk_t="CMYK: " +cmyk[0]+"%, "+cmyk[1]+"%, "+cmyk[2]+ "%, "+cmyk[3] +"%"
        val hsl_t ="HSL: " + hsl[0] +"°, " + hsl[1]+ "%, "+ hsl[2]+"%"
        val hsv_t ="HSV: " + hsv[0] + "°, "+ hsv[1] +"%, "+hsv[2] +"%"
        return rgb_t +"\n"+ hex_t +"\n" +cmyk_t + "\n"+ hsl_t +"\n" +hsv_t
    }
    fun get_hex(): String {
        return this.hex
    }

    fun get_rgb(): MutableList<Int> {
        //hex2rgb(hex)
        return this.rgb
    }

    fun get_hsl(): MutableList<Int>{
        //rgb2hsl()
        return this.hsl
    }

    fun get_cmyk(): MutableList<Int>{
        //rgb2cmyk()
        return this.cmyk
    }

    fun get_hsv(): MutableList<Int>{
        return this.hsv
    }

    fun set_hsv(hsv_t: MutableList<Int>){
        var i =0
        while(i<hsv_t.size){
            hsv.add(hsv_t[i])
            i++
        }
    }


    private fun hex2rgb(hex: String){ //converts hex code to rgb
        var hex_a = mutableListOf("1")
        var count = 0
        while (count < hex.length) {
            hex_a.add(hex.substring(count, count + 1))
            count++
        }
        hex_a.removeAt(0)

        count = 0
        for (i in hex_a) {
            when (i) {
                "A" -> {
                    hex_a[count] = "10"
                }
                "B" -> {
                    hex_a[count] = "11"
                }
                "C" -> {
                    hex_a[count] = "12"
                }
                "D" -> {
                    hex_a[count] = "13"
                }
                "E" -> {
                    hex_a[count] = "14"
                }
                "F" -> {
                    hex_a[count] = "15"
                }
            }
            count++
        }
        val R = hex_a[0].toInt() * 16 + hex_a[1].toInt()
        val G = hex_a[2].toInt() * 16 + hex_a[3].toInt()
        val B = hex_a[4].toInt() * 16 + hex_a[5].toInt()
        var hex_f = mutableListOf(R, G, B)
        this.rgb = hex_f //sets field

    }

    private fun rgb2hsl() {
        var temp_rgb = mutableListOf(1.0)

        var h =0.0
        var s =0.0
        var l =0.0

        var count = 0
        while (count < 3) {
            temp_rgb.add(rgb[count].toDouble() / 255)
            count++
        }
        temp_rgb.removeAt(0)

        var min = temp_rgb[0]
        var max = temp_rgb[0]
        var maxcolor = 0


        var i = 0
        while (i < temp_rgb.size - 1) {
            if (temp_rgb[i + 1] <= min) {
                min = temp_rgb[i + 1]
            }
            if (temp_rgb[i + 1] >= max) {
                max = temp_rgb[i + 1]
                maxcolor = i + 1
            }
            i++
        }

        when(maxcolor){
            0 -> {
                h = (temp_rgb[1] - temp_rgb[2]) / (max - min)
            }
            1 -> {
                h = 2 + (temp_rgb[2] - temp_rgb[0]) / (max - min)
            }
            2 -> {
                h = 4 + (temp_rgb[0] - temp_rgb[1]) / (max - min)

            }
        }
        h=h*60
        if (h < 0) {
            h = h + 360 }
        if (h>360){
            h=h-360
        }
        l = (min + max) / 2;
        if (min == max) {
            s = 0.0;
        } else {
            if (l < 0.5) {
                s = (max - min) / (max + min);
            } else {
                s = (max - min) / (2 - max - min);
            }
        }
        s*=100 //turns them into percents
        l*=100
        this.hsl = mutableListOf(h.roundToInt(), s.roundToInt(), l.roundToInt());

    }

    private fun rgb2cmyk() {
        var c =0.0
        var m =0.0
        var y =0.0
        var k =0.0

        var r = rgb[0].toDouble() / 255
        var g = rgb[1].toDouble()/ 255
        var b =rgb[2].toDouble()/ 255
        var max = Math.max(r, g)
        max = Math.max(max, b)
        k = 1-max
        if (k!=1.0){
            c = (1 - r - k) / (1 - k)
            m = (1 - g - k) / (1 - k)
            y = (1 - b - k) / (1 - k)

        }
        this.cmyk = mutableListOf((c * 100).roundToInt(), (m * 100).roundToInt(), (y * 100).roundToInt(), (k * 100).roundToInt())
    }

    private fun rgb2hsv(){

        var h =0.0;
        var s =0.0
        var v =0.0

        val r = rgb[0].toDouble()/255
        val g = rgb[1].toDouble()/255
        val b = rgb[2].toDouble()/255

        var max = Math.max(r, g)
        max = Math.max(max, b)
        var min = Math.min(r, g)
        min = Math.min(g, b)

        v = max
        val delta = max-min

        if(max!=0.0){
            s= delta/max }
        else{
            h=-1.0 }
        if(r==max){ //between yellow and magenta
            h=(g-b)/delta
        }
        else if (g==max){ //between cyan and yellow
            h=2 + (b-r)/delta }
        else{ //between magenta and cyan
            h = 4 +(r-g)/delta }
        h*=60
        if (h < 0){
            h+=360 }

        this.hsv = mutableListOf(h.roundToInt(), (s * 100).roundToInt(), (v * 100).roundToInt())
    }

    private fun hsv2rgb(){
        val s = hsv[1].toDouble()/100.0
        val v = hsv[2].toDouble()/100.0
        val C = s*v
        val X = C*(1- abs(((hsv[0] / 60) % 2) - 1))
        val m = v-C
        var r =0.0
        var g = 0.0
        var b = 0.0

        if(hsv[0]>=0 && hsv[0]<60){
            r=C; g =X;
        }
        else if(hsv[0]>=60 && hsv[0]<120){
            r=X; g=C;
        }
        else if(hsv[0]>=120 && hsv[0]<180){
            g=C; b=X;
        }
        else if(hsv[0]>=180 && hsv[0]<240){
            g=X; b =C;
        }
        else if(hsv[0]>=240 && hsv[0]< 300){
            r=X; b =C;
        }
        else{
            r=C; b=X
        }
        this.rgb = mutableListOf(((r + m) * 255).roundToInt(), ((g + m) * 255).roundToInt(), ((b + m) * 255).toInt())

    }


    //code below is referenced from https://www.cocyer.com/convert-rgb-color-to-hex-code-in-java/

    fun rgb2hex() {
        // Function to convert the RGB code to Hex color code
        var h = java.lang.String.format("#%02X%02X%02X", this.rgb[0], this.rgb[1], this.rgb[2])
        this.hex = h.substring(1)
    }


}