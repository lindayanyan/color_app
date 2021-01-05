package com.example.colorapp

class color_group() {
    //purpose of this class: creates a triplet of split complementary colors
    lateinit var c1: ColorCode
    lateinit var c2: ColorCode
    lateinit var c3: ColorCode

    //hex of c2 and c3 is incorrect
    constructor(c: ColorCode) : this() {
        c1=c
        makeC2()
        makeC3()
    }

    private fun makeC2(){
        val h_i = c1.get_hsv()
        var h_n = h_i[0]+150
        if(h_n>360){
            h_n-=360
        }
        c2 = ColorCode(mutableListOf(h_n, h_i[1], h_i[2]))
    }

    private fun makeC3(){
        val h_i = c1.get_hsv()
        var h_n=h_i[0]-150
        if(h_n<0){
            h_n+=360
        }
        c3 = ColorCode(mutableListOf(h_n, h_i[1], h_i[2]))
    }

    fun get_c2(): ColorCode {
        return c2
    }


    fun get_c3(): ColorCode{
        return c3
    }

    fun get_c1(): ColorCode{
        return c1
    }


}