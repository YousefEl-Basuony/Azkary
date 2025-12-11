package com.example.azkary.Presentaion.Screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class Saly : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "الصلاة على النبي",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "العودة",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF6A11CB),
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    scrollBehavior = scrollBehavior
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            PrayerCounterScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PrayerCounterScreen(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    val animatedSize by animateDpAsState(
        targetValue = if (count > 0) 220.dp else 200.dp,
        animationSpec = tween(durationMillis = 300),
        label = "circleAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6A11CB),
                        Color(0xFF2575FC)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // النص العلوي
            Text(
                text = "اللهم صل على سيدنا محمد",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // الدائرة التفاعلية
            PrayerCircle(
                count = count,
                size = animatedSize,
                onCircleClick = { count++ }
            )

            // العداد
            Text(
                text = "عدد المرات: $count",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp)
            )

            // نص توجيهي
            Text(
                text = "اضغط على الدائرة للصلاة على النبي",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            // زر إعادة الضبط
            Text(
                text = "إعادة الضبط",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clickable { count = 0 }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun PrayerCircle(
    count: Int,
    size: Dp,
    onCircleClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clickable { onCircleClick() }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // رسم الدائرة الخلفية
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFD700),
                        Color(0xFFFFA000)
                    ),
                    center = Offset(size.toPx() / 2, size.toPx() / 2),
                    radius = size.toPx() / 2
                ),
                radius = size.toPx() / 2
            )

            // رسم حدود الدائرة
            drawCircle(
                color = Color.White,
                radius = size.toPx() / 2,
                style = Stroke(width = 4f)
            )

            // رسم زخرفة إسلامية داخل الدائرة
            val centerX = size.toPx() / 2
            val centerY = size.toPx() / 2
            val decorationSize = size.toPx() / 3

            drawCircle(
                center = Offset(centerX, centerY),
                radius = decorationSize / 2,
                color = Color(0xFF6A11CB)
            )
        }

        // النص داخل الدائرة
        Text(
            text = "ﷺ",
            color = Color.White,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold
        )
    }
}