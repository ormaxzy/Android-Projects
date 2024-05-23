package com.example.cityweather.data

import android.content.Context

class SharedPrefsManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
}
