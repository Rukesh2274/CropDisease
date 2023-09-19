package com.example.cropdisease.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
class SessionViewModel(context: Context) : ViewModel() {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

    // Define a function to check if the user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isUserLoggedIn", false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        try {
            sharedPreferences.edit().putBoolean("isUserLoggedIn", isLoggedIn).apply()
            Log.d("SessionViewModel", "isLoggedIn set to $isLoggedIn")
        } catch (e: Exception) {
            Log.e("SessionViewModel", "Error setting logged in state or navigating", e)
        }
    }


    fun logoutUser() {
        try {
            sharedPreferences.edit().putBoolean("isUserLoggedIn", false).apply()
            Log.d("SessionViewModel", "User logged out")
        } catch (e: Exception) {
            Log.e("SessionViewModel", "Error logging out or navigating", e)
        }
    }
}