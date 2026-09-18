package com.mobileinsider.app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MobileInsiderRepository {

    suspend fun news() =
        withContext(Dispatchers.IO) {

            ApiClient.request(
                ApiClient.NEWS
            )
        }

    suspend fun social() =
        withContext(Dispatchers.IO) {

            ApiClient.request(
                ApiClient.SOCIAL
            )
        }
}
