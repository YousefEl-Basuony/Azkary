package com.example.azkary.Presentaion.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import java.util.*

class Morning : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        // تحميل خط عربي مخصص


        // تدرج لوني للخلفية - ألوان هادئة تناسب الصباح
        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFfdfcfb), Color(0xFFe2d1c3))
        )

        // ألوان البطاقات المتناسقة مع موضوع الصباح
        val morningColors = listOf(
            Color(0xFFFFF8E1), // أصفر فاتح جداً
            Color(0xFFFFF3E0), // كريمي فاتح
            Color(0xFFFFECB3), // أصفر فاتح
            Color(0xFFFFE0B2)  // برتقالي فاتح جداً
        )

        val azkarList = listOf(
            Triple(
                "أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيم\n" +
                        "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ " +
                        "لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلَّا بِإِذْنِهِ ۚ " +
                        "يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ " +
                        "وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضِ ۖ وَلَا يَؤُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ.",
                1,
                "https://server7.mp3quran.net/dosari/002.mp3" // آية الكرسي
            ),

            Triple(
                "سورة الإخلاص \n" +
                        " قُلْ هُوَ اللَّهُ أَحَدٌ\n" +
                        "اللَّهُ الصَّمَدُ\n" +
                        "لَمْ يَلِدْ وَلَمْ يُولَدْ\n" +
                        "(وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",
                3,
                "https://server7.mp3quran.net/dosari/112.mp3" // سورة الإخلاص
            ),
            Triple(
                "سورة الفلق \n" +
                        "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ\n" +
                        "مِن شَرِّ مَا خَلَقَ\n" +
                        "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ\n" +
                        "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ\n" +
                        "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ",
                3,
                "https://server7.mp3quran.net/dosari/113.mp3" // سورة الفلق
            ),
            Triple(
                "سورة الناس – 114\n" +
                        "قُلْ أَعُوذُ بِرَبِّ النَّاسِ\n" +
                        "مَلِكِ النَّاسِ\n" +
                        "إِلَٰهِ النَّاسِ\n" +
                        "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ\n" +
                        "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ\n" +
                        "مِنَ الْجِنَّةِ وَ النَّاسِ",
                3,
                "https://server7.mp3quran.net/dosari/114.mp3" // سورة الناس
            ),
            Triple("أَصْـبَحْنا وَأَصْـبَحَ المُـلْكُ لله وَالحَمدُ لله", 1, null),
            Triple("اللّهـمَّ أَنْتَ رَبِّي لا إلهَ إلاّ أَنْتَ ...", 1, null),
            Triple("رَضيـتُ بِاللهِ رَبَّـاً وَبِالإسْلامِ ديـناً ...", 3, null),
            Triple("اللّهُـمَّ إِنِّـي أَصْبَـحْتُ أُشْـهِدُك ...", 4, null),
            Triple("اللّهُـمَّ ما أَصْبَـَحَ بي مِـنْ نِعْـمَةٍ ...", 1, null),
            Triple("حَسْبِـيَ اللّهُ لا إلهَ إلاّ هُوَ عَلَيهِ تَوَكَّـلتُ ...", 7, null),
            Triple("بِسـمِ اللهِ الذي لا يَضُـرُّ مَعَ اسمِـهِ شَيءٌ ...", 3, null),
            Triple("اللّهُـمَّ بِكَ أ_صْـبَحْنا وَبِكَ أَمْسَـينا ...", 1, null),
            Triple("أَصْبَـحْـنا عَلَى فِطْرَةِ الإسْلاَمِ ...", 1, null),
            Triple("سُبْحـانَ اللهِ وَبِحَمْـدِهِ ...", 100, null),
            Triple("اللّهُـمَّ عافِـني في بَدَنـي ...", 3, null),
            Triple("اللّهُـمَّ إِنّـي أَعـوذُ بِكَ مِنَ الْكُـفر ...", 3, null),
            Triple("اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ ...", 1, null),
            Triple("يَا حَيُّ يَا قيُّومُ بِرَحْمَتِكَ أسْتَغِيثُ ...", 3, null),
            Triple("أ_صْبَـحْـنا وَأَصْبَـحْ المُـلكُ للهِ رَبِّ العـالَمـين ...", 1, null),
            Triple("اللّهُـمَّ عالِـمَ الغَـيْبِ وَالشّـهادَةِ ...", 1, null),
            Triple("أَعـوذُ بِكَلِمـاتِ اللّهِ التّـامّـاتِ ...", 3, null),
            Triple("اللَّهُمَّ صَلِّ وَسَلِّمْ وَبَارِكْ على نَبِيِّنَا مُحمَّد ...", 10, null),
            Triple("اللَّهُمَّ إِنَّا نَعُوذُ بِكَ مِنْ أَنْ نُشْرِكَ بِكَ شَيْئًا ...", 3, null),
            Triple("اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْهَمِّ وَالْحَزَنِ ...", 3, null),
            Triple("أسْتَغْفِرُ اللهَ العَظِيمَ ...", 3, null),
            Triple("يَا رَبِّ , لَكَ الْحَمْدُ ...", 3, null),
            Triple("اللَّهُمَّ إِنِّي أَسْأَلُكَ عِلْمًا نَافِعًا ...", 1, null),
            Triple("اللَّهُمَّ أَنْتَ رَبِّي لا إِلَهَ إِلاّ أَنْتَ ...", 1, null),
            Triple("لَا إلَه إلّا اللهُ وَحْدَهُ ...", 100, null),
            Triple("سُبْحـانَ اللهِ وَبِحَمْـدِهِ ...", 100, null),
            Triple("أسْتَغْفِرُ اللهَ وَأتُوبُ إلَيْهِ ...", 100, null)        )

        // حالة تشغيل/إيقاف صوت الشيخ
        var isSheikhPlaying by remember { mutableStateOf(false) }
        var currentSheikhAudio by remember { mutableStateOf("") }

        // MediaPlayer لتشغيل صوت الشيخ
        val sheikhMediaPlayer = remember { MediaPlayer() }

        // تهيئة مشغل صوت الشيخ
        LaunchedEffect(Unit) {
            try {
                sheikhMediaPlayer.setOnCompletionListener {
                    isSheikhPlaying = false
                    currentSheikhAudio = ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // وظيفة تشغيل صوت الشيخ
        fun playSheikhAudio(audioUrl: String?) {
            if (audioUrl.isNullOrEmpty()) return

            try {
                if (isSheikhPlaying) {
                    sheikhMediaPlayer.stop()
                    sheikhMediaPlayer.reset()
                }

                sheikhMediaPlayer.setDataSource(audioUrl)
                sheikhMediaPlayer.prepareAsync()
                sheikhMediaPlayer.setOnPreparedListener {
                    it.start()
                    isSheikhPlaying = true
                    currentSheikhAudio = audioUrl
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // تهيئة محرك النص إلى كلام
        var tts: TextToSpeech? by remember { mutableStateOf(null) }
        var currentlySpeaking by remember { mutableStateOf(-1) }

        LaunchedEffect(Unit) {
            tts = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    tts?.language = Locale("ar")
                }
            }
        }

        // حالة لحساب إجمالي الأذكار المكتملة
        var completedAzkar by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient).statusBarsPadding()
        ) {
            // شريط العنوان المخصص
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF8D6E63), Color(0xFF5D4037))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            navigator.pop()
                            tts?.stop()
                            if (isSheikhPlaying) {
                                sheikhMediaPlayer.stop()
                                sheikhMediaPlayer.release()
                            }
                        },
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "العودة",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Text(
                        text = "أذكار الصباح",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    // مؤشر التقدم
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color(0x44FFFFFF), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "$completedAzkar/${azkarList.size}",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // شريط التقدم العام
            LinearProgressIndicator(
                progress = if (azkarList.size > 0) completedAzkar.toFloat() / azkarList.size else 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = Color(0xFF4E342E),
                trackColor = Color.LightGray.copy(alpha = 0.4f)
            )

            // رسالة تشغيل صوت الشيخ
            if (isSheikhPlaying) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VolumeUp,
                            contentDescription = "جاري التشغيل",
                            tint = Color(0xFF2E7D32),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "جاري تشغيل تلاوة الشيخ ياسر الدوسري",
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold,
                         )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = {
                                sheikhMediaPlayer.stop()
                                isSheikhPlaying = false
                                currentSheikhAudio = ""
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Pause,
                                contentDescription = "إيقاف",
                                tint = Color(0xFFD32F2F)
                            )
                        }
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(azkarList) { index, (text, maxCount, audioUrl) ->
                    var count by remember { mutableStateOf(0) }
                    val cardColor = morningColors[index % morningColors.size]
                    val isCompleted = count >= maxCount
                    val isSpeaking = currentlySpeaking == index
                    val hasSheikhAudio = !audioUrl.isNullOrEmpty()

                    // تحديث العداد عند اكتمال الذكر
                    LaunchedEffect(isCompleted) {
                        val previouslyCompleted = count >= maxCount
                        if (isCompleted != previouslyCompleted) {
                            if (isCompleted) completedAzkar++ else completedAzkar--
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCompleted) Color(0xFFE8F5E9) else cardColor
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // رقم الذكر
                            Text(
                                text = "ذكر ${index + 1}",
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                 modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // نص الذكر مع تحسين الخط
                            Text(
                                text = text,
                                fontSize = 20.sp,
                                color = Color(0xFF37474F),
                                textAlign = TextAlign.Start,
                                lineHeight = 32.sp,
                                fontWeight = FontWeight.Normal,
                                 modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // أزرار الاستماع
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // زر الاستماع للذكر بصوت التطبيق
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(
                                        onClick = {
                                            if (isSpeaking) {
                                                tts?.stop()
                                                currentlySpeaking = -1
                                            } else {
                                                // إيقاف أي قراءة سابقة وصوت الشيخ
                                                if (currentlySpeaking != -1) {
                                                    tts?.stop()
                                                }
                                                if (isSheikhPlaying) {
                                                    sheikhMediaPlayer.stop()
                                                    isSheikhPlaying = false
                                                    currentSheikhAudio = ""
                                                }
                                                currentlySpeaking = index
                                                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                                            }
                                        },
                                        modifier = Modifier.size(48.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isSpeaking) Icons.Filled.Pause else Icons.Filled.VolumeUp,
                                            contentDescription = "استمع إلى الذكر",
                                            tint = if (isSpeaking) Color(0xFF4E342E) else Color(0xFF757575),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }

                                    Text(
                                        text = if (isSpeaking) "جاري التلاوة..." else "التطبيق",
                                        color = if (isSpeaking) Color(0xFF4E342E) else Color(0xFF757575),
                                        fontSize = 14.sp,
                                         modifier = Modifier.padding(start = 4.dp)
                                    )
                                }

                                // زر الاستماع بصوت الشيخ ياسر الدوسري (إذا متوفر)
                                if (hasSheikhAudio) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = if (currentSheikhAudio == audioUrl && isSheikhPlaying) "جاري التشغيل..." else "الشيخ الدوسري",
                                            color = if (currentSheikhAudio == audioUrl && isSheikhPlaying) Color(0xFF4E342E) else Color(0xFF757575),
                                            fontSize = 14.sp,
                                             modifier = Modifier.padding(end = 4.dp)
                                        )

                                        IconButton(
                                            onClick = {
                                                if (currentSheikhAudio == audioUrl && isSheikhPlaying) {
                                                    sheikhMediaPlayer.stop()
                                                    isSheikhPlaying = false
                                                    currentSheikhAudio = ""
                                                } else {
                                                    // إيقاف أي قراءة سابقة
                                                    if (currentlySpeaking != -1) {
                                                        tts?.stop()
                                                        currentlySpeaking = -1
                                                    }
                                                    playSheikhAudio(audioUrl)
                                                }
                                            },
                                            modifier = Modifier.size(48.dp)
                                        ) {
                                            Icon(
                                                imageVector = if (currentSheikhAudio == audioUrl && isSheikhPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                                contentDescription = "استمع بصوت الشيخ الدوسري",
                                                tint = if (currentSheikhAudio == audioUrl && isSheikhPlaying) Color(0xFF4E342E) else Color(0xFF4E342E),
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            // شريط تقدم للذكر الفردي
                            LinearProgressIndicator(
                                progress = if (maxCount > 0) count.toFloat() / maxCount else 0f,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .padding(horizontal = 8.dp),
                                color = if (isCompleted) Color(0xFF388E3C) else Color(0xFF4E342E),
                                trackColor = Color.LightGray.copy(alpha = 0.4f)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // عداد التكرار مع أزرار التحكم
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // زر التكرار التلقائي
                                IconButton(
                                    onClick = {
                                        if (count < maxCount) count = maxCount
                                    },
                                    modifier = Modifier.size(50.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayArrow,
                                        contentDescription = "إكمال كل التكرارات",
                                        tint = if (isCompleted) Color.Gray else Color(0xFF4E342E),
                                        modifier = Modifier.size(28.dp)
                                    )
                                }

                                // زر التقليل
                                IconButton(
                                    onClick = { if (count > 0) count-- },
                                    modifier = Modifier.size(50.dp),
                                    enabled = count > 0
                                ) {
                                    Text(
                                        "-",
                                        fontSize = 28.sp,
                                        color = if (count == 0) Color.Gray else Color(0xFFD32F2F),
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                // العداد
                                Box(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp)
                                        .background(
                                            color = if (isCompleted) Color(0xFFE8F5E9) else Color.White,
                                            shape = RoundedCornerShape(12.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$count / $maxCount",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCompleted) Color(0xFF388E3C) else Color(0xFF4E342E),
                                     )
                                }

                                // زر الزيادة
                                IconButton(
                                    onClick = { if (count < maxCount) count++ },
                                    modifier = Modifier.size(50.dp),
                                    enabled = count < maxCount
                                ) {
                                    Text(
                                        "+",
                                        fontSize = 28.sp,
                                        color = if (count >= maxCount) Color.Gray else Color(0xFF388E3C),
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                // زر إعادة التعيين
                                IconButton(
                                    onClick = { count = 0 },
                                    modifier = Modifier.size(50.dp),
                                    enabled = count > 0
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Refresh,
                                        contentDescription = "إعادة التعيين",
                                        tint = if (count == 0) Color.Gray else Color(0xFF1976D2),
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }

                            // مؤشر الإكمال
                            if (isCompleted) {
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = "مكتمل",
                                        tint = Color(0xFF388E3C),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "مكتمل",
                                        color = Color(0xFF388E3C),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                     )
                                }
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
                if (isSheikhPlaying) {
                    sheikhMediaPlayer.stop()
                    sheikhMediaPlayer.release()
                }
            }
        }
    }
}