package com.example.mysuperlist.data




data class card(val id:Int = System.currentTimeMillis().toInt(), var Title:String, var Progress:Int = 0, var list: MutableList<inn_card>){
    override fun toString(): String {
        return "\n Id= ${id}\n Title= ${Title}\n Progress= ${Progress}%\n InnCard= ${list}\n"
    }
}

data class inn_card( var inn_id:Int = 0, var check:Boolean = false,var inn_title:String){
    override fun toString(): String {
        return "\n       Id= ${inn_id}\n       Title= ${inn_title}\n       Check= ${check}}\n"
    }
}