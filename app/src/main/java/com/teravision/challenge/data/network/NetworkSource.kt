package com.teravision.challenge.data.network

class NetworkSource(
    private val networkController: NetworkController
) {
    fun hasInternetConnection(): Boolean {
        return networkController.hasInternetConnection()
    }
}