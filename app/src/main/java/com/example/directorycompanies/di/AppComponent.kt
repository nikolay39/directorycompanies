package com.example.directorycompanies.di


import android.content.Context
import com.example.directorycompanies.App
import com.example.directorycompanies.di.modules.RepoModule
import com.example.directorycompanies.di.modules.view.ViewComponent
import com.example.directorycompanies.di.modules.view.ViewModule
import com.example.directorycompanies.di.modules.viewmodel.MapViewModel
import com.example.directorycompanies.di.modules.viewmodel.ViewModelBuilderModule
import com.example.directorycompanies.repository.CompanyRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RepoModule::class, ViewModelBuilderModule::class,
    ViewModule::class, MapViewModel::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context,
                    ): AppComponent
    }
    fun viewComponent(): ViewComponent.Factory
    fun inject(application: App)
    val quoutesRepo: CompanyRepository

}
