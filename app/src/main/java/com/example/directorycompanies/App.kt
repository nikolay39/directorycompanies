package com.example.directorycompanies

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.multidex.BuildConfig
import androidx.work.*
import com.example.directorycompanies.di.AppComponent
import com.example.directorycompanies.di.DaggerAppComponent
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
/*
Источники данных: https://lifehack.studio/test_task/test.php и https://lifehack.studio/test_task/test.php?id=ид_компании
Язык программирования - Kotlin. Минимальная версия SDK - 19
* */

//https://developers.google.com/maps/documentation/urls/get-started
//https://www.tutorialspoint.com/how-to-display-html-in-textview-in-android
//https://github.com/google-developer-training/android-kotlin-fundamentals-apps/tree/master/RecyclerViewClickHandler

//https://github.com/google-developer-training/android-kotlin-fundamentals-apps/blob/master/RecyclerViewClickHandler/app/src/main/java/com/example/android/trackmysleepquality/MainActivity.kt
open class App: Application(){



    val appComponent: AppComponent by lazy {
        initializeComponent()
    }
    open fun initializeComponent(): AppComponent {
        val appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
        return appComponent
    }
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}