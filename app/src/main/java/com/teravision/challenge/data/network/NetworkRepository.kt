package com.teravision.challenge.data.network

class NetworkRepository(
    private val networkSource: NetworkSource
) {
    fun hasInternetConnection(): Boolean {
        return networkSource.hasInternetConnection()
    }
}