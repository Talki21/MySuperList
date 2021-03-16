package com.example.mysuperlist.data




data class card(val id:Int = System.currentTimeMillis().toInt(), var Title:String, var Progress:Int = 0, var list: MutableList<inn_card>)

data class inn_card( var inn_id:Int = 0, var check:Boolean = false,var inn_title:String = "")