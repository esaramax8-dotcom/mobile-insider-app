package com.mobileinsider.app.data

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ApiClient {

    const val SITE =
        "https://mobileinsider.netlify.app"

    const val AI =
        "$SITE/.netlify/functions/ai-chat"

    const val NEWS =
        "$SITE/.netlify/functions/fetch-news"

    const val SOCIAL =
        "$SITE/.netlify/functions/social-stats"

    fun request(
        url: String,
        method: String = "GET",
        body: String? = null
    ): JSONObject {

        val connection =
            URL(url).openConnection()
                as HttpURLConnection

        try {

            connection.requestMethod =
                method

            connection.connectTimeout =
                20_000

            connection.readTimeout =
                60_000

            connection.setRequestProperty(
                "Accept",
                "application/json"
            )

            if (body != null) {

                connection.doOutput = true

                connection.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )

                connection.outputStream.use {
                    output ->

                    output.write(
                        body.toByteArray(
                            Charsets.UTF_8
                        )
                    )
                }
            }

            val code =
                connection.responseCode

            val stream =
                if (code in 200..299) {

                    connection.inputStream

                } else {

                    connection.errorStream
                }

            val response =
                BufferedReader(
                    InputStreamReader(
                        stream
                    )
                ).use {
                    it.readText()
                }

            if (code !in 200..299) {

                throw Exception(
                    try {
                        JSONObject(response)
                            .optString(
                                "error",
                                "Request failed."
                            )
                    } catch (_: Exception) {
                        "Request failed."
                    }
                )
            }

            return JSONObject(response)

        } finally {

            connection.disconnect()
        }
    }
}
