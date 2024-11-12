package ru.firevawe.firevawemobile

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.firevawe.firevawemobile.data.di.base.baseDataModule
import ru.firevawe.firevawemobile.domain.di.base.baseDomainModule
import ru.firevawe.firevawemobile.presentation.di.base.basePresentationModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(baseDataModule, baseDomainModule, basePresentationModule)
        }
    }
}