package com.teravision.challenge

import android.app.Application
import com.teravision.challenge.framework.di.dataModule
import com.teravision.challenge.framework.di.usersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class TeravisionChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    /**
     * Initialize Koin
     */
    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@TeravisionChallengeApplication)
            modules(
                listOf(
                    dataModule,
                    usersModule
                )
            )
        }
    }
}