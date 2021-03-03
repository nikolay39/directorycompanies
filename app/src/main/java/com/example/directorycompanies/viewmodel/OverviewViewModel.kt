package com.example.directorycompanies.viewmodel


import android.content.Context
import androidx.lifecycle.*
import com.example.directorycompanies.repository.CompanyRepository
import com.example.directorycompanies.repository.network.entity.CompanyDetail
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import timber.log.Timber
import javax.inject.Inject

class OverviewViewModel @Inject constructor(val repository: CompanyRepository, applicationContext: Context) : ViewModel() {
    lateinit var  companies: LiveData<List<CompanyPreview>>
    private val _navigateCompanyDetail = MutableLiveData<CompanyDetail>()
    val navigateCompanyDetail: LiveData<CompanyDetail>
        get() = _navigateCompanyDetail


    private val _navigateToIdDetail = MutableLiveData<Int?>()
    val navigateToIdDetail
        get() = _navigateToIdDetail


    fun init(){
        Timber.i("init vieawModel");
        companies = LiveDataReactiveStreams.fromPublisher(repository.downloadCompanies());
    }
    fun onDetailClicked(id: Int) {
        _navigateToIdDetail.value = id
    }

    fun onDetailNavigated() {
        _navigateToIdDetail.value = null
    }
}
