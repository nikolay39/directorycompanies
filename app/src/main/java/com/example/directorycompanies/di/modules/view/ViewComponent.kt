package com.example.directorycompanies.di.modules.view

import com.example.directorycompanies.view.MainActivity
import com.example.directorycompanies.view.OverviewFragment
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@ActivityScope
@Subcomponent
interface ViewComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create():ViewComponent
    }
    fun inject(mainActivity: MainActivity)
    fun inject(overviewFragment: OverviewFragment)
}