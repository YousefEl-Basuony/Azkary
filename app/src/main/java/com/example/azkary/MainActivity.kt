package com.example.azkary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import com.example.azkary.Presentaion.Screens.Azkary
import com.example.azkary.Presentaion.Screens.SplashScreen
import com.example.azkary.ui.theme.AzkaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AzkaryTheme {
                Navigator(SplashScreen())

            }
        }
    }
}







