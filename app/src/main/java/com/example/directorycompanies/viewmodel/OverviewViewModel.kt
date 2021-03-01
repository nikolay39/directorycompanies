package com.example.android.usdTrigger.viewmodel


import android.content.Context
import androidx.lifecycle.*
import com.example.directorycompanies.repository.CompanyRepository
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class OverviewViewModel @Inject constructor(val repository: CompanyRepository, applicationContext: Context) : ViewModel() {
    lateinit var  quotes: LiveData<List<CompanyPreview>>

    fun init(){
        Timber.i("init vieamodel");
        Timber.i("currentTime ${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}")
        quotes = LiveDataReactiveStreams.fromPublisher(repository.downloadCompanies());
    }
}
