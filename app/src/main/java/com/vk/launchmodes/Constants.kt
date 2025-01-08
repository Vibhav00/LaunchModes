package com.vk.launchmodes

object Constants {
   private var margintop = 300
    fun getMarginTop():Int{
        margintop +=80
        return margintop
    }
    fun setMarginTop(){
        margintop -=80
    }
}