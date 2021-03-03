package com.example.directorycompanies.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.example.directorycompanies.viewmodel.DetailViewModel
import com.example.directorycompanies.viewmodel.OverviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MapViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(OverviewViewModel::class)
    abstract fun bindMainViewModel(overviewViewModel: OverviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}