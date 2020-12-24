package com.teravision.challenge.framework.di

import com.teravision.challenge.data.UsersRepository
import com.teravision.challenge.data.UsersSource
import com.teravision.challenge.data.api.UserServiceApi
import com.teravision.challenge.data.network.NetworkController
import com.teravision.challenge.data.network.NetworkRepository
import com.teravision.challenge.data.network.NetworkSource
import com.teravision.challenge.domain.usecase.GetUsersUseCase
import com.teravision.challenge.ui.UsersVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val usersModule = module(override = true) {
    viewModel { UsersVM(get(), get()) }

    single { GetUsersUseCase(get()) }
    single { UsersRepository(get(), get()) }

    single { NetworkRepository(get()) }
    single { NetworkSource(get()) }
    single { NetworkController(androidContext()) }

    single { UsersSource() }

    single { get<Retrofit>().create(UserServiceApi::class.java) }

}

