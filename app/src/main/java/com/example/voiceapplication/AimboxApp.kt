package com.example.voiceapplication

import android.app.Application
import android.content.Context
import com.justai.aimybox.Aimybox
import com.justai.aimybox.api.aimybox.AimyboxDialogApi
import com.justai.aimybox.components.AimyboxProvider
import com.justai.aimybox.core.Config
import com.justai.aimybox.speechkit.google.platform.GooglePlatformSpeechToText
import com.justai.aimybox.speechkit.google.platform.GooglePlatformTextToSpeech
import java.util.*

class AimboxApp : Application(), AimyboxProvider {

    companion object {
        private const val API_KEY = "rMzHedR4UEYIz44lWEIyDHl7E4TDgBp2"
    }

    override val aimybox by lazy {
        createAimybox(this)
    }

    private fun createAimybox(context: Context): Aimybox {
        val unitId = UUID.randomUUID().toString()

        val textToSpeech = GooglePlatformTextToSpeech(context)
        val speechToText = GooglePlatformSpeechToText(context)

        val dialogApi = AimyboxDialogApi(API_KEY, unitId)

        val aimyboxConfig = Config.create(speechToText, textToSpeech, dialogApi)
        return Aimybox(aimyboxConfig, context)

    }
}