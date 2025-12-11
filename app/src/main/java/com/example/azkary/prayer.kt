package com.example.azkary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import android.speech.tts.TextToSpeech
import java.util.*

class Prayer : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        // تدرج لوني للخلفية
        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFE8F5E9), Color(0xFFF1F8E9))
        )

        // ألوان البطاقات
        val cardColorsList = listOf(
            Color(0xFFFFF59D), // أصفر فاتح
            Color(0xFFA5D6A7), // أخضر فاتح
            Color(0xFF81D4FA), // أزرق فاتح
            Color(0xFFF48FB1)  // وردي فاتح
        )

        val azkarList = listOf(
            Pair("أَسْتَغْفِرُ الله، أَسْتَغْفِرُ الله، أَسْتَغْفِرُ الله.", 3),
            Pair("اللّهُـمَّ أَنْـتَ السَّلامُ ، وَمِـنْكَ السَّلام ...", 1),
            Pair("لا إلهَ إلاّ اللّهُ وحدَهُ لا شريكَ لهُ ...", 1),
            Pair("سُـبْحانَ اللهِ، والحَمْـدُ لله ، واللهُ أكْـبَر.", 33),
            Pair("بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم ...", 3),
            Pair("أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيم ...", 1),
            Pair("لا إلهَ إلاّ اللّهُ وحْـدَهُ لا شريكَ لهُ ...", 10),
            Pair("اللّهُـمَّ إِنِّـي أَسْأَلُـكَ عِلْمـاً نافِعـاً ...", 1),
            Pair("اللَّهُمَّ أَجِرْنِي مِنْ النَّار.", 7),
            Pair("اللَّهُمَّ أَعِنِّي عَلَى ذِكْرِكَ وَشُكْرِكَ ...", 1)
        )

        // تهيئة محرك النص إلى كلام
        val context = LocalContext.current
        var tts: TextToSpeech? by remember { mutableStateOf(null) }

        LaunchedEffect(Unit) {
            tts = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    tts?.language = Locale("ar")
                }
            }
        }

        // حالة للتحكم في الصوت
        var currentlySpeaking by remember { mutableStateOf(-1) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient)
        ) {
            // شريط العنوان
            TopAppBar(
                title = {
                    Text(
                        text = "أذكار الصلاة",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF388E3C), // لون أخضر
                    titleContentColor = Color.White
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(azkarList) { index, (text, maxCount) ->
                    var count by remember { mutableStateOf(0) }
                    val cardColor = cardColorsList[index % cardColorsList.size]
                    val isSpeaking = currentlySpeaking == index

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // رقم الذكر
                            Text(
                                text = "ذكر ${index + 1}",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = text,
                                fontSize = 22.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                lineHeight = 30.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // زر الاستماع للدعاء
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                IconButton(
                                    onClick = {
                                        if (isSpeaking) {
                                            tts?.stop()
                                            currentlySpeaking = -1
                                        } else {
                                            currentlySpeaking = index
                                            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.VolumeUp,
                                        contentDescription = "استمع إلى الذكر",
                                        tint = if (isSpeaking) Color(0xFF388E3C) else Color.Gray
                                    )
                                }

                                Text(
                                    text = if (isSpeaking) "جاري القراءة..." else "استمع إلى الذكر",
                                    color = if (isSpeaking) Color(0xFF388E3C) else Color.Gray,
                                    modifier = Modifier.padding(top = 12.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // زر التقليل
                                IconButton(
                                    onClick = { if (count > 0) count-- },
                                    modifier = Modifier.size(50.dp)
                                ) {
                                    Text(
                                        "-",
                                        fontSize = 24.sp,
                                        color = if (count == 0) Color.Gray else Color(0xFFD32F2F)
                                    )
                                }

                                Text(
                                    text = "$count / $maxCount",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                // زر الزيادة
                                IconButton(
                                    onClick = { if (count < maxCount) count++ },
                                    modifier = Modifier.size(50.dp)
                                ) {
                                    Text(
                                        "+",
                                        fontSize = 24.sp,
                                        color = if (count >= maxCount) Color.Gray else Color(0xFF388E3C)
                                    )
                                }
                            }

                            // مؤشر الإكمال
                            if (count >= maxCount) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "✓ مكتمل",
                                    color = Color(0xFF388E3C),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // التوقف عن القراءة عند مغادرة الشاشة
        DisposableEffect(Unit) {
            onDispose {
                tts?.stop()
                tts?.shutdown()
            }
        }
    }
}