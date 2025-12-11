package com.example.azkary

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import android.speech.tts.TextToSpeech
import java.util.*

class Evening : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        // تحميل خط عربي مخصص


        // تدرج لوني للخلفية بلون المساء - ألوان هادئة تناسب المساء
        val backgroundGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB), Color(0xFF90CAF9))
        )

        // ألوان البطاقات المتناسقة مع موضوع المساء
        val eveningColors = listOf(
            Color(0xFFE3F2FD), // أزرق فاتح جداً
            Color(0xFFBBDEFB), // أزرق فاتح
            Color(0xFF90CAF9), // أزرق
            Color(0xFF9FA8DA)  // بنفسجي فاتح
        )

        val azkarList = listOf(
            Pair(
                "أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ\n" +
                        "اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ. [آية الكرسى - البقرة 255]. \n" +
                        "من قالها حين يمسى أجير من الجن حتى يصبح.",
                1
            ),
            Pair(
                "أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ\n" +
                        "اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ. [آية الكرسى - البقرة 255]. \n" +
                        "من قالها حين يمسى أجير من الجن حتى يصبح.",
                1
            ),
            Pair(
                "آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ. لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَّسِينَآ أَوْ أَخْطَأْنَا رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ. [البقرة 285 - 286]. \n" +
                        "من قرأ آيتين من آخر سورة البقرة في ليلة كفتاه.",
                1
            ),
            Pair(
                "بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\n" +
                        "قُلْ هُوَ ٱللَّهُ أَحَدٌ، ٱللَّهُ ٱلصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌۢ. \n" +
                        "من قالها حين يمسى كفته من كل شىء.",
                3
            ),
            Pair(
                "بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\n" +
                        "قُلْ أَعُوذُ بِرَبِّ ٱلْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِى ٱلْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ.",
                3
            ),
            Pair(
                "بِسْمِ اللهِ الرَّحْمنِ الرَّحِيم\n" +
                        "قُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ، مَلِكِ ٱلنَّاسِ، إِلَٰهِ ٱلنَّاسِ، مِن شَرِّ ٱلْوَسْوَاسِ ٱلْخَنَّاسِ، ٱلَّذِى يُوَسْوِسُ فِى صُدُورِ ٱلنَّاسِ، مِنَ ٱلْجِنَّةِ وَٱلنَّاسِ.",
                3
            ),
            Pair(
                "أَمْسَيْـنا وَأَمْسـى المـلكُ لله وَالحَمدُ لله ، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُـلكُ ولهُ الحَمْـد، وهُوَ على كلّ شَيءٍ قدير ، رَبِّ أسْـأَلُـكَ خَـيرَ ما في هـذهِ اللَّـيْلَةِ وَخَـيرَ ما بَعْـدَهـا ، وَأَعـوذُ بِكَ مِنْ شَـرِّ ما in هـذهِ اللَّـيْلةِ وَشَرِّ ما بَعْـدَهـا ، رَبِّ أَعـوذُبِكَ مِنَ الْكَسَـلِ وَسـوءِ الْكِـبَر ، رَبِّ أَعـوذُ بِكَ مِنْ عَـذابٍ في النّـارِ وَعَـذابٍ في القَـبْر.",
                1
            ),
            Pair(
                "اللّهـمَّ أَنْتَ رَبِّـي لا إلهَ إلاّ أَنْتَ ، خَلَقْتَنـي وَأَنا عَبْـدُك ، وَأَنا عَلـى عَهْـدِكَ وَوَعْـدِكَ ما اسْتَـطَعْـت ، أَعـوذُبِكَ مِنْ شَـرِّ ما صَنَـعْت ، أَبـوءُ لَـكَ بِنِعْـمَتِـكَ عَلَـيَّ وَأَبـوءُ بِذَنْـبي فَاغْفـِرْ لي فَإِنَّـهُ لا يَغْـفِرُ الذُّنـوبَ إَلاّ أَنْتَ . \n" +
                        "من قالها موقنا بها حين يمسى ومات من ليلته دخل الجنة.",
                1
            ),
            Pair(
                "رَضيـتُ بِاللهِ رَبَّـاً وَبِالإسْلامِ ديـناً وَبِمُحَـمَّدٍ صلى الله عليه وسلم نَبِيّـاً. \n" +
                        "من قالها حين يمسى كان حقا على الله أن يرضيه يوم القيامة.",
                3
            ),
            Pair(
                "اللّهُـمَّ إِنِّـي أَمسيتُ أُشْـهِدُك ، وَأُشْـهِدُ حَمَلَـةَ عَـرْشِـك ، وَمَلَائِكَتَكَ ، وَجَمـيعَ خَلْـقِك ، أَنَّـكَ أَنْـتَ اللهُ لا إلهَ إلاّ أَنْـتَ وَحْـدَكَ لا شَريكَ لَـك ، وَأَنَّ ُ مُحَمّـداً عَبْـدُكَ وَرَسـولُـك. \n" +
                        "من قالها أعتقه الله من النار.",
                4
            ),
            Pair(
                "اللّهُـمَّ ما أَمسى بي مِـنْ نِعْـمَةٍ أَو بِأَحَـدٍ مِـنْ خَلْـقِك ، فَمِـنْكَ وَحْـدَكَ لا شريكَ لَـك ، فَلَـكَ الْحَمْـدُ وَلَـكَ الشُّكْـر. \n" +
                        "من قالها حين يمسى أدى شكر يومه.",
                1
            ),
            Pair(
                "حَسْبِـيَ اللّهُ لا إلهَ إلاّ هُوَ عَلَـيهِ تَوَكَّـلتُ وَهُوَ رَبُّ العَرْشِ العَظـيم. \n" +
                        "من قالها كفاه الله ما أهمه من أمر الدنيا والأخرة.",
                7
            ),
            Pair(
                "بِسـمِ اللهِ الذي لا يَضُـرُّ مَعَ اسمِـهِ شَيءٌ في الأرْضِ وَلا in السّمـاءِ وَهـوَ السّمـيعُ العَلـيم. \n" +
                        "لم يضره من الله شيء.",
                3
            ),
            Pair(
                "اللّهُـمَّ بِكَ أَمْسَـينا وَبِكَ أَصْـبَحْنا، وَبِكَ نَحْـيا وَبِكَ نَمُـوتُ وَإِلَـيْكَ الْمَصِيرُ.",
                1
            ),
            Pair(
                "أَمْسَيْنَا عَلَى فِطْرَةِ الإسْلاَمِ، وَعَلَى كَلِمَةِ الإِخْلاَصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِينَا إبْرَاهِيمَ حَنِيفاً مُسْلِماً وَمَا كَانَ مِنَ المُشْرِكِينَ.",
                1
            ),
            Pair(
                "سُبْحـانَ اللهِ وَبِحَمْـدِهِ عَدَدَ خَلْـقِه ، وَرِضـا نَفْسِـه ، وَزِنَـةَ عَـرْشِـه ، وَمِـدادَ كَلِمـاتِـه.",
                3
            ),
            Pair(
                "اللّهُـمَّ عافِـني في بَدَنـي ، اللّهُـمَّ عافِـني in سَمْـعي ، اللّهُـمَّ عافِـني in بَصَـري ، لا إلهَ إلاّ أَنْـتَ.",
                3
            ),
            Pair(
                "اللّهُـمَّ إِنّـي أَعـوذُ بِكَ مِنَ الْكُـفر ، وَالفَـقْر ، وَأَعـوذُ بِكَ مِنْ عَذابِ القَـبْر ، لا إلهَ إلاّ أَنْـتَ.",
                3
            ),
            Pair(
                "اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ in الدُّنْـيا وَالآخِـرَة ، اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ in ديني وَدُنْـيايَ وَأهْـلي وَمالـي ، اللّهُـمَّ اسْتُـرْ عـوْراتي وَآمِـنْ رَوْعاتـi ، اللّهُـمَّ احْفَظْـني مِن بَـينِ يَدَيَّ وَمِن خَلْفـi وَعَن يَمـيني وَعَن شِمـالي ، وَمِن فَوْقـi ، وَأَعـوذُ بِعَظَمَـتِكَ أَن أُغْـتالَ مِن تَحْتـi.",
                1
            ),
            Pair(
                "يَا حَيُّ يَا قيُّومُ بِرَحْمَتِكَ أسْتَغِيثُ أصْلِحْ لِي شَأنِي كُلَّهُ وَلاَ تَكِلْنِي إلَى نَفْسِي طَـرْفَةَ عَيْنٍ.",
                3
            ),
            Pair(
                "أَمْسَيْنا وَأَمْسَى الْمُلْكُ للهِ رَبِّ الْعَالَمَيْنِ، اللَّهُمَّ إِنَّي أسْأَلُكَ خَيْرَ هَذَه اللَّيْلَةِ فَتْحَهَا ونَصْرَهَا، ونُوْرَهَا وبَرَكَتهَا، وَهُدَاهَا، وَأَعُوذُ بِكَ مِنْ شَرِّ مَا فيهِا وَشَرَّ مَا بَعْدَهَا.",
                1
            ),
            Pair(
                "اللّهُـمَّ عالِـمَ الغَـيْبِ وَالشّـهادَةِ فاطِـرَ السّماواتِ وَالأرْضِ رَبَّ كـلِّ شَـيءٍ وَمَليـكَه ، أَشْهَـدُ أَنْ لا إِلـهَ إِلاّ أَنْت ، أَعـوذُ بِكَ مِن شَـرِّ نَفْسـi وَمِن شَـرِّ الشَّيْـطانِ وَشِرْكِهِ ، وَأَنْ أَقْتَـرِفَ عَلـى نَفْسـi سوءاً أَوْ أَجُـرَّهُ إِلـى مُسْـلِم.",
                1
            ),
            Pair(
                "أَعـوذُ بِكَلِمـاتِ اللّهِ التّـامّـاتِ مِنْ شَـرِّ ما خَلَـق.",
                3
            ),
            Pair(
                "اللَّهُمَّ صَلِّ وَسَلِّمْ وَبَارِكْ على نَبِيِّنَا مُحمَّد. \n" +
                        "من صلى على حين يمسى ادركته شفاعتى يوم القيامة.",
                10
            ),
            Pair(
                "اللَّهُمَّ إِنَّا نَعُوذُ بِكَ مِنْ أَنْ نُشْرِكَ بِكَ شَيْئًا نَعْلَمُهُ ، وَنَسْتَغْفِرُكَ لِمَا لَا نَعْلَمُهُ.",
                3
            ),
            Pair(
                "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ الْهَمِّ وَالْحَزَنِ، وَأَعُوذُ بِكَ مِنْ الْعَجْزِ وَالْكَسَلِ، وَأَعُوذُ بِكَ مِنْ الْجُبْنِ وَالْبُخْلِ، وَأَعُوذُ بِكَ مِنْ غَلَبَةِ الدَّيْنِ، وَقَهْرِ الرِّجَالِ.",
                3
            ),
            Pair(
                "أسْتَغْفِرُ اللهَ العَظِيمَ الَّذِي لاَ إلَهَ إلاَّ هُوَ، الحَيُّ القَيُّومُ، وَأتُوبُ إلَيهِ.",
                3
            ),
            Pair(
                "يَا رَبِّ , لَكَ الْحَمْدُ كَمَا يَنْبَغِي لِجَلَالِ وَجْهِكَ , وَلِعَظِيمِ سُلْطَانِكَ.",
                3
            ),
            Pair(
                "لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءِ قَدِيرِ. \n" +
                        "كانت له عدل عشر رقاب، وكتبت له مئة حسنة، ومحيت عنه مئة سيئة، وكانت له حرزا من الشيطان.",
                100
            ),
            Pair(
                "سُبْحـانَ اللهِ وَبِحَمْـدِهِ. \n" +
                        "حُطَّتْ خَطَايَاهُ وَإِنْ كَانَتْ مِثْلَ زَبَدِ الْبَحْرِ.",
                100
            )        )

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
                .background(brush = backgroundGradient).systemBarsPadding()
        ) {
            // شريط العنوان المخصص
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF1565C0), Color(0xFF0D47A1))
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
                        text = "أذكار المساء",
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
                color = Color(0xFF1565C0),
                trackColor = Color.LightGray.copy(alpha = 0.4f)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(azkarList) { index, (text, maxCount) ->
                    var count by remember { mutableStateOf(0) }
                    val cardColor = eveningColors[index % eveningColors.size]
                    val isCompleted = count >= maxCount
                    val isSpeaking = currentlySpeaking == index

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

                            // زر الاستماع للذكر
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = {
                                        if (isSpeaking) {
                                            tts?.stop()
                                            currentlySpeaking = -1
                                        } else {
                                            // إيقاف أي قراءة سابقة
                                            if (currentlySpeaking != -1) {
                                                tts?.stop()
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
                                        tint = if (isSpeaking) Color(0xFF1565C0) else Color(0xFF757575),
                                        modifier = Modifier.size(30.dp)
                                    )
                                }

                                Text(
                                    text = if (isSpeaking) "جاري التلاوة..." else "التطبيق",
                                    color = if (isSpeaking) Color(0xFF1565C0) else Color(0xFF757575),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            // شريط تقدم للذكر الفردي
                            LinearProgressIndicator(
                                progress = if (maxCount > 0) count.toFloat() / maxCount else 0f,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .padding(horizontal = 8.dp),
                                color = if (isCompleted) Color(0xFF388E3C) else Color(0xFF1565C0),
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
                                        tint = if (isCompleted) Color.Gray else Color(0xFF1565C0),
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
                                        color = if (isCompleted) Color(0xFF388E3C) else Color(0xFF1565C0),
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
            }
        }
    }
}