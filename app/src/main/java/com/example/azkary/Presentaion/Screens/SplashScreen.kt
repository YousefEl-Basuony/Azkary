package com.example.azkary.Presentaion.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.azkary.R
import kotlinx.coroutines.delay

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = true) {
            delay(3000)
            navigator.replace(Azkary())
        }

        // Background Gradient (Dark Blue/Teal)
        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF152A42), // Dark Blue
                Color(0xFF1D5A75), // Teal/Blue
                Color(0xFF287994)  // Lighter Teal
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient),
            contentAlignment = Alignment.Center
        ) {
            // Decorative background elements could be added here if needed

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                // Top Arabic Text (Bismilah or Salawat if available, using placeholder or icon)
                Text(
                    text = "ﷺ",
                    color = Color(0xFFFFD700), // Gold
                    fontSize = 32.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Main Mosque Image/Icon
                Image(
                    painter = painterResource(id = R.drawable.g),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(bottom = 24.dp),
                    contentScale = ContentScale.Fit
                )

                // App Title
                Text(
                    text = "تطبيق أذكاري",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Subtitle
                Text(
                    text = "ما تنساش تصلِّي على النبي",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
