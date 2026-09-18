package com.mobileinsider.app.ai

import android.util.Base64
import com.mobileinsider.app.data.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class AiRepository {

    suspend fun send(

        message: String,

        history: List<AiMessage>,

        file: File? = null,

        fileMimeType: String = ""

    ): AiResult = withContext(
        Dispatchers.IO
    ) {

        val historyJson =
            JSONArray()

        history
            .takeLast(8)
            .forEach { item ->

                historyJson.put(

                    JSONObject()
                        .put(
                            "role",
                            item.role
                        )
                        .put(
                            "text",
                            item.text
                        )
                )
            }

        val body =
            JSONObject()

        body.put(
            "message",
            message
        )

        body.put(
            "history",
            historyJson
        )

        body.put(
            "userName",
            ""
        )

        if (file != null) {

            val bytes =
                file.readBytes()

            val base64 =
                Base64.encodeToString(
                    bytes,
                    Base64.NO_WRAP
                )

            val inline =
                JSONObject()
                    .put(
                        "mimeType",
                        fileMimeType.ifBlank {
                            "application/octet-stream"
                        }
                    )
                    .put(
                        "data",
                        base64
                    )

            body.put(
                "file",
                JSONObject()
                    .put(
                        "inlineData",
                        inline
                    )
            )

            body.put(
                "fileName",
                file.name
            )

            body.put(
                "fileMimeType",
                fileMimeType
            )
        }

        val response =
            ApiClient.request(
                ApiClient.AI,
                "POST",
                body.toString()
            )

        AiResult(

            text =
                response.optString(
                    "text",
                    "AI returned no text."
                ),

            imageBase64 =
                response
                    .optJSONObject("image")
                    ?.optString(
                        "data",
                        null
                    ),

            imageMimeType =
                response
                    .optJSONObject("image")
                    ?.optString(
                        "mimeType",
                        null
                    )
        )
    }
}

data class AiResult(

    val text: String,

    val imageBase64: String? = null,

    val imageMimeType: String? = null
)
