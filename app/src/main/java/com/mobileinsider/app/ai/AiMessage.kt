package com.mobileinsider.app.ai

data class AiMessage(

    val id: Long =
        System.currentTimeMillis(),

    val role: String,

    val text: String,

    val attachmentName: String? = null,

    val imageBase64: String? = null,

    val imageMimeType: String? = null
)
