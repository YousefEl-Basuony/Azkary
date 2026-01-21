package com.example.azkary.Presentaion.Utils

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.*

class AudioManager(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    var currentPlayingFile by mutableStateOf<String?>(null)
        private set

    fun playAudio(fileName: String, onCompletion: () -> Unit = {}) {
        stopAudio()

        val resId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        if (resId != 0) {
            mediaPlayer = MediaPlayer.create(context, resId).apply {
                setOnCompletionListener {
                    stopAudio()
                    onCompletion()
                }
                start()
            }
            currentPlayingFile = fileName
        } else {
            android.widget.Toast.makeText(context, "الملف الصوتي غير موجود: $fileName", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    fun stopAudio() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        mediaPlayer?.release()
        mediaPlayer = null
        currentPlayingFile = null
    }

    fun isPlaying(fileName: String): Boolean {
        return currentPlayingFile == fileName && mediaPlayer?.isPlaying == true
    }
}

@Composable
fun rememberAudioManager(): AudioManager {
    val context = androidx.compose.ui.platform.LocalContext.current
    val audioManager = remember { AudioManager(context) }
    
    DisposableEffect(Unit) {
        onDispose {
            audioManager.stopAudio()
        }
    }
    
    return audioManager
}
