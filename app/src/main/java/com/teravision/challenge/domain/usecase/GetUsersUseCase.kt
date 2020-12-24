package com.teravision.challenge.domain.usecase

import com.teravision.challenge.data.UsersRepository
import com.teravision.challenge.domain.entity.UserEntity

class GetUsersUseCase(
    private val usersRepository: UsersRepository)
{
    suspend operator fun invoke() : List<UserEntity> = usersRepository.getUsers()
}