package com.example.azkary.Presentaion.Utils

import android.content.Context
import android.content.SharedPreferences

class AzkarPersistenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("azkar_progress", Context.MODE_PRIVATE)

    fun getCount(key: String, defaultCount: Int): Int {
        return sharedPreferences.getInt(key, defaultCount)
    }

    fun saveCount(key: String, count: Int) {
        sharedPreferences.edit().putInt(key, count).apply()
    }
    
    // Optional: Reset a specific key (if needed later)
    fun resetCount(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
