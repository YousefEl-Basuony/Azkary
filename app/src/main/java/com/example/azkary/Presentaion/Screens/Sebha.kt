package com.example.azkary.Presentaion.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalView
import android.view.HapticFeedbackConstants

class Sebha : Screen {
    @Composable
    override fun Content() {
        val context = androidx.compose.ui.platform.LocalContext.current
        val persistenceManager = remember { com.example.azkary.Presentaion.Utils.AzkarPersistenceManager(context) }
        
        // List of Azkar
        val azkarList = listOf(
            "سبحان الله",
            "الحمد لله",
            "الله أكبر",
            "لا إله إلا الله",
            "أستغفر الله العظيم",
            "لا حول ولا قوة إلا بالله",
            "اللهم صل على محمد",
            "سبحان الله وبحمده"
        )
        
        var currentZikrIndex by remember { mutableStateOf(0) }
        val currentZikr = azkarList[currentZikrIndex]
        
        // Load persisted count for the current Zikr
        var count by remember(currentZikr) { 
            mutableStateOf(persistenceManager.getCount("sebha_$currentZikr", 0)) 
        }

        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFE0F7FA), Color(0xFF80DEEA))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Zikr Display
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .clickable {
                        // Cycle through Azkar list
                        currentZikrIndex = (currentZikrIndex + 1) % azkarList.size
                        // Count will automatically update via the remember(currentZikr) block
                    }
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentZikr,
                        fontSize = 36.sp, // Enlarged for elderly
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF006064),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 50.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "(اضغط هنا لتغيير الذكر)",
                        fontSize = 18.sp, // Enlarged for elderly (was 14)
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Counter Display
            Text(
                text = "$count",
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006064),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Animation State
            val scale by androidx.compose.animation.core.animateFloatAsState(
                targetValue = if (count % 2 == 0) 1f else 0.95f, // Subtle pulse effect on click
                animationSpec = androidx.compose.animation.core.spring(
                    dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
                    stiffness = androidx.compose.animation.core.Spring.StiffnessLow
                )
            )
            val view = androidx.compose.ui.platform.LocalView.current

            // Main Count Button
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(Color(0xFF0097A7))
                    .clickable { 
                        count++
                        persistenceManager.saveCount("sebha_$currentZikr", count)
                        view.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "سبح",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Reset Button
            IconButton(
                onClick = { 
                    count = 0 
                    persistenceManager.saveCount("sebha_$currentZikr", 0)
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "تصفير",
                    tint = Color(0xFF006064),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
