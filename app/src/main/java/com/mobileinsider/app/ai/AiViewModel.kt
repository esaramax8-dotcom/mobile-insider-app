package com.mobileinsider.app.ai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.File

class AiViewModel : ViewModel() {

    private val repository =
        AiRepository()

    var messages by
        mutableStateOf(
            listOf<AiMessage>()
        )
        private set

    var loading by
        mutableStateOf(false)
        private set

    var error by
        mutableStateOf<String?>(null)
        private set

    var selectedFile by
        mutableStateOf<File?>(null)
        private set

    var selectedMimeType by
        mutableStateOf("")
        private set

    fun selectFile(
        file: File,
        mimeType: String
    ) {

        if (
            file.length() >
            4 * 1024 * 1024
        ) {

            error =
                "File must be smaller than 4 MB."

            return
        }

        selectedFile =
            file

        selectedMimeType =
            mimeType

        error = null
    }

    fun removeFile() {

        selectedFile = null

        selectedMimeType = ""
    }

    fun send(
        text: String
    ) {

        val clean =
            text.trim()

        if (
            clean.isBlank() &&
            selectedFile == null
        ) {
            return
        }

        if (loading) {
            return
        }

        val attachment =
            selectedFile

        val mime =
            selectedMimeType

        val userMessage =
            AiMessage(

                role = "user",

                text =
                    clean.ifBlank {
                        "Please analyze the attached file."
                    },

                attachmentName =
                    attachment?.name
            )

        val previous =
            messages

        messages =
            previous + userMessage

        loading = true

        error = null

        selectedFile = null

        selectedMimeType = ""

        viewModelScope.launch {

            try {

                val result =
                    repository.send(

                        message =
                            userMessage.text,

                        history =
                            previous,

                        file =
                            attachment,

                        fileMimeType =
                            mime
                    )

                messages =
                    messages +
                            AiMessage(

                                role =
                                    "assistant",

                                text =
                                    result.text,

                                imageBase64 =
                                    result.imageBase64,

                                imageMimeType =
                                    result.imageMimeType
                            )

            } catch (e: Exception) {

                error =
                    e.message
                        ?: "AI request failed."

            } finally {

                loading = false
            }
        }
    }

    fun clear() {

        messages =
            emptyList()

        error = null

        selectedFile = null
    }

    fun clearError() {

        error = null
    }
}
