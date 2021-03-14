package com.example.mysuperlist.data

import java.sql.Time
import java.util.*


var lastID = 1

data class card(val id:Int = System.currentTimeMillis().toInt(), var Title:String, var Progress:Int = 0)

data class inn_card(val id:Int = 0, var check:Boolean = false,var inn_title:String)