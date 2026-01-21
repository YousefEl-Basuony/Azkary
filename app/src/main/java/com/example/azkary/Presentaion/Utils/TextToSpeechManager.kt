package com.example.azkary.Presentaion.Utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

class TextToSpeechManager(context: Context) {
    private var textToSpeech: TextToSpeech? = null
    private var isInitialized = false
    private var _currentSpeakingId = mutableStateOf<String?>(null)

    val currentSpeakingId: String?
        get() = _currentSpeakingId.value

    init {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Try to set Egyptian Arabic, fall back to generic Arabic
                val egyptLocale = Locale("ar", "EG")
                val result = textToSpeech?.setLanguage(egyptLocale)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Fallback to standard Arabic
                    val arabicResult = textToSpeech?.setLanguage(Locale("ar"))
                    if (arabicResult == TextToSpeech.LANG_MISSING_DATA || arabicResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(context, "الاللغة العربية غير مدعومة في جهازك", Toast.LENGTH_SHORT).show()
                    }
                }
                isInitialized = true
                
                // Add listener to detect when speaking ends to update state
                textToSpeech?.setOnUtteranceProgressListener(object : android.speech.tts.UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        _currentSpeakingId.value = utteranceId
                    }

                    override fun onDone(utteranceId: String?) {
                        _currentSpeakingId.value = null
                    }

                    override fun onError(utteranceId: String?) {
                        _currentSpeakingId.value = null
                    }
                })
            } else {
                Toast.makeText(context, "فشل تهيئة القارئ الصوتي", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun speak(text: String, id: String = "MessageId") {
        if (!isInitialized || textToSpeech == null) return

        if (isSpeaking(id)) {
            stop()
        } else {
            // Use QUEUE_FLUSH to interrupt any current speech
            val params = android.os.Bundle()
            params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, id)
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, params, id)
            _currentSpeakingId.value = id
        }
    }

    fun isSpeaking(id: String): Boolean {
        return _currentSpeakingId.value == id
    }

    fun stop() {
        textToSpeech?.stop()
        _currentSpeakingId.value = null
    }

    fun shutdown() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}

@Composable
fun rememberTextToSpeechManager(): TextToSpeechManager {
    val context = LocalContext.current
    val ttsManager = remember { TextToSpeechManager(context) }
    
    DisposableEffect(Unit) {
        onDispose {
            ttsManager.shutdown()
        }
    }
    
    return ttsManager
}
