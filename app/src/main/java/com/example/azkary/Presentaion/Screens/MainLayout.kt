package com.example.azkary.Presentaion.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.BlurCircular
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.ui.graphics.Brush
 import kotlinx.coroutines.delay


class MainLayout : Screen {
    @Composable
    override fun Content() {
        // 0 = Sebha, 1 = Home (Center/Default), 2 = About
        var selectedItem by remember { mutableStateOf(1) }

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.
                    background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF1A4F8E), Color(0xFF55BDF3))
                        )
                    ),
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                    tonalElevation = 0.dp,
                    actions = {
                        // Left Side - Sebha
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedItem = 0 }
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                        ) {
                             Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(width = 64.dp, height = 32.dp)
                                    .background(
                                        color = if (selectedItem == 0) Color.White else Color.Transparent,
                                        shape = androidx.compose.foundation.shape.CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.BlurCircular,
                                    contentDescription = "السبحة",
                                    tint = if (selectedItem == 0) Color(0xFF1A4F8E) else Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = "السبحة",
                                fontSize = 12.sp,
                                fontWeight = if (selectedItem == 0) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedItem == 0) Color.White else Color.White.copy(alpha = 0.7f)
                            )
                        }

                        // Spacer for FAB - Wider gap for the 56dp FAB
                        Spacer(modifier = Modifier.weight(1f))

                        // Right Side - About
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedItem = 2 }
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.MenuBook,
                                contentDescription = "عن التطبيق",
                                tint = if (selectedItem == 2) Color(0xFFFFA726) else Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.size(26.dp)
                            )
                            Text(
                                text = "عن التطبيق",
                                fontSize = 13.sp,
                                fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedItem == 2) Color(0xFFFFA726) else Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { selectedItem = 1 },
                    containerColor = Color.White,
                    contentColor = Color(0xFF1A4F8E),
                    shape = androidx.compose.foundation.shape.CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp, pressedElevation = 10.dp),
                    modifier = Modifier
                        .size(56.dp)
                        .offset(y = (75).dp) // Lift above the bar
                ) {
                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                   ) {
                         Icon(
                            imageVector = Icons.Rounded.Home,
                            contentDescription = "الرئيسية",
                            modifier = Modifier.size(28.dp)
                        )
                   }
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                when (selectedItem) {
                    0 -> Sebha().Content()
                    1 -> Azkary().Content()
                    2 -> About().Content()
                }

                // --- In-App Reminder Logic ---
                var showReminder by remember { mutableStateOf(false) }
                val azkarReminders = remember {
                    listOf(
                        "سبحان الله",
                        "الحمد لله",
                        "الله أكبر",
                        "لا إله إلا الله",
                        "أستغفر الله",
                        "لا حول ولا قوة إلا بالله",
                        "صلِّ على النبي ﷺ"
                    )
                }
                var currentZikr by remember { mutableStateOf(azkarReminders.first()) }

                LaunchedEffect(Unit) {
                    while(true) {
                        currentZikr = azkarReminders.random() // Pick a random Zikr
                        showReminder = true
                        delay(7000L) // Show for 7 seconds
                        showReminder = false
                        delay(10 * 1000L) // Wait 10 seconds
                    }
                }

                // Reminder Banner
                AnimatedVisibility(
                    visible = showReminder,
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp),
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)), // Orange/Gold
                        shape = RoundedCornerShape(30.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .clickable { showReminder = false } // Dismiss on click
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                        ) {
                             Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                             )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = currentZikr,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                // -----------------------------
            }
        }
    }
}
