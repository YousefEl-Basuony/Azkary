package com.example.azkary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class Azkary : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF1A4F8E),
                Color(0xFF2D8BC8),
                Color(0xFF55BDF3)
            )
        )


        








        val morningGradient = Brush.horizontalGradient(

            colors = listOf(Color(0xFFFFA726), Color(0xFFFF9800))
        )
        val eveningGradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF26C6DA), Color(0xFF00ACC1))
        )
        val prayerGradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF66BB6A), Color(0xFF4CAF50))
        )
        val prophetGradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFFAB47BC), Color(0xFF9C27B0))
        )

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundGradient).systemBarsPadding().padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.islamic_pray_svgrepo_com),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alpha = 0.1f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 40.dp)
                    ) {
                        Text(
                            text = "ﷺ",
                            fontSize = 42.sp,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "تطبيق أذكاري",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "ما تنساش تصلِّي على النبي",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.9f),
                            textAlign = TextAlign.Center
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.mosque_svgrepo_com),
                        contentDescription = "مسجد توضيحي",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(220.dp)
                            .padding(vertical = 16.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        PrayerOptionButton(
                            gradient = morningGradient,
                            text = "أذكار الصباح",
                            iconResId = R.drawable.sun_svgrepo_com,
                            onClick = { navigator.push(Morning()) }
                        )

                        PrayerOptionButton(
                            gradient = eveningGradient,
                            text = "أذكار المساء",
                            iconResId = R.drawable.moon_phases_moon_svgrepo_com,
                            onClick = { navigator.push(Evening()) }
                        )

                        PrayerOptionButton(
                            gradient = prayerGradient,
                            text = "أذكار بعد الصلاة",
                            iconResId = R.drawable.donation_svgrepo_com,
                            onClick = { navigator.push(Prayer()) }
                        )

                        PrayerOptionButton(
                            gradient = prophetGradient,
                            text = "صلي على النبي",
                            iconResId = R.drawable.praying_hands_svgrepo_com,
                            onClick = { navigator.push(Saly()) }
                        )
                    }

                    // نص المشاركة والمطور
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 24.dp)
                    ) {
                        Text(
                            text = "تطوير: يوسف محمد البسيوني",
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        // زر المشاركة
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .clickable { /* مشاركة التطبيق */ }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                Icons.Default.Share,
                                contentDescription = "مشاركة التطبيق",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "شارك التطبيق لنيل الثواب",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }


                        // معلومات المطور

                    }
                }
            }
        }
    }
}

// مكون زر الخيار المخصص
@Composable
fun PrayerOptionButton(
    gradient: Brush,
    text: String,
    iconResId: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = text,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}