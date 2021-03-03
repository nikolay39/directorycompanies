package com.example.directorycompanies.viewmodel


import android.content.Context
import androidx.lifecycle.*
import com.example.directorycompanies.repository.CompanyRepository
import com.example.directorycompanies.repository.network.entity.CompanyDetail
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DetailViewModel @Inject constructor(val repository: CompanyRepository, applicationContext: Context)
    : ViewModel() {
    lateinit var company: LiveData<CompanyDetail>

    fun init(id: Int){
        Timber.i("init vieamodel");
        company = LiveDataReactiveStreams.fromPublisher(repository.downloadInfoCompanyById(id).toFlowable());
    }
}
