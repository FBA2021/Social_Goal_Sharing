package com.example.social_goal_sharing.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreference {
    private val fileName: String="SHARED_FILENAME"

    fun hasSavedContacts (context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return preferences.getBoolean("contactsSaved", false)
    }

    @SuppressLint("SuspiciousIndentation")
    fun setContactsSave(context: Context) {
    val preferences: SharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        preferences.edit().putBoolean("contactsSaved", true).apply()
    }

    fun removeAccessToken(context: Context){
        val preference: SharedPreferences =context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        preference.edit().remove("accessToken").apply()
    }

    fun getAccessToken(context: Context) : String{
        val preference: SharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        return preference.getString("accessToken","").toString()
    }

    fun setAccessToken(context: Context, accessToken :String){
       val preferences:SharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        preferences.edit().putString("accessToken",accessToken).apply()
    }
}