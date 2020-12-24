package com.teravision.challenge.data.api

import retrofit2.http.*

interface UserServiceApi {

    @GET("users")
    suspend fun getUsers(): List<UserRaw>
}