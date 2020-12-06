package com.mediquo.breakingbad.presentation

import android.app.Application
import com.mediquo.breakingbad.presentation.injection.appModule
import com.mediquo.breakingbad.presentation.injection.listModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BreakingBadApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BreakingBadApp)
            modules(listOf(
                appModule,
                listModule
            ))
        }
    }
}