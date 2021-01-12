package com.example.colorapp.data

import com.example.colorapp.ColorCode

class DataSource() {
    lateinit var codes: List<String>
    constructor(codes: List<String>?) : this() {
        if (codes != null) {
            this.codes=codes
        }
    }
    fun loadColors(): MutableList<ColorCode> {
        var cc_list = mutableListOf<ColorCode>()
        for(i in codes){
            cc_list.add(ColorCode(i))
        }
        return cc_list
    }



    }