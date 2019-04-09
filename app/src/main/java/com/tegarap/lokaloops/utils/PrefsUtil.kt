package com.tegarap.lokaloops.utils

import android.content.Context
import android.content.SharedPreferences
import com.tegarap.lokaloops.models.UserResponse

class PrefsUtil{

    constructor(context : Context?){
        prefs = context!!.getSharedPreferences("terserah", Context.MODE_PRIVATE)
    }

    private lateinit var prefs : SharedPreferences


    fun saveUser(user : UserResponse){
        val editor = prefs.edit()
        editor.putString("username", user.username)
        editor.putString("password",user.password)
        editor.apply()
    }



}