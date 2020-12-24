package com.teravision.challenge.domain.usecase.network

import com.teravision.challenge.data.network.NetworkRepository

class HaveConnectivityUseCase(private val networkRepository: NetworkRepository) {

    operator fun invoke(): Boolean {
       return networkRepository.hasInternetConnection()
    }

}