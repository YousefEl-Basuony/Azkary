package com.example.azkary.Presentaion.Screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class About : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF1A4F8E), Color(0xFF55BDF3))
        )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
                .systemBarsPadding()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(23.dp))
            
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "عن التطبيق",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "تطبيق أذكاري",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF1A4F8E),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))

                    // Feature 1
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End // Right to Left alignment for Arabic
                    ) {
                        Text(
                            text = "تسهيل قراءة الأذكار اليومية",
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.End,
                            modifier = Modifier.weight(1f).padding(end = 12.dp)
                        )
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFA726), modifier = Modifier.size(24.dp))
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))

                    // Feature 2
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "خالي تماماً من الإعلانات",
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.End,
                            modifier = Modifier.weight(1f).padding(end = 12.dp)
                        )
                        Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF29B6F6), modifier = Modifier.size(24.dp))
                    }

                     Spacer(modifier = Modifier.height(12.dp))

                    // Feature 3
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                         Text(
                            text = "تصميم بسيط وسهل لكبار السن",
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                             textAlign = androidx.compose.ui.text.style.TextAlign.End,
                             modifier = Modifier.weight(1f).padding(end = 12.dp)
                        )
                         Icon(Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFEF5350), modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Feature 4
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "دعم القراءة الصوتية للأذكار",
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.End,
                            modifier = Modifier.weight(1f).padding(end = 12.dp)
                        )
                         Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFF66BB6A), modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "تطوير: يوسف البسيوني",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A4F8E),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "نسألكم الدعاء له و لوالديه",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Share Button
            Button(
                onClick = {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, "حمل تطبيق أذكاري وشارك في الخير: https://play.google.com/store/apps/details?id=com.example.azkary")
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, "شارك التطبيق")
                    context.startActivity(shareIntent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)), // Orange
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 32.dp)
            ) {
                Icon(Icons.Default.Share, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "شارك التطبيق لنيل الثواب سوا",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
