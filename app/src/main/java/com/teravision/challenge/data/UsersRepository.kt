package com.teravision.challenge.data

import com.teravision.challenge.data.api.UserServiceApi
import com.teravision.challenge.domain.entity.UserEntity

class UsersRepository(
    private val userApi: UserServiceApi,
    private val usersSource: UsersSource
) {
    suspend fun getUsers(): List<UserEntity> {
        try {
            val response = userApi.getUsers()
            return usersSource.mapUsers(response)
        } catch (e: Exception) {
            throw e
        }
    }
}