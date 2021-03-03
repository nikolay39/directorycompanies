package com.example.directorycompanies.repository

import com.example.directorycompanies.repository.network.ApiService
import com.example.directorycompanies.repository.network.entity.CompanyDetail
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class CompanyRepository @Inject constructor(val api: ApiService) {

    fun downloadCompanies(): Flowable<List<CompanyPreview>> {
        Timber.i("downloadQuotes run")
        return api.getCompanesPrieview()
                .subscribeOn(Schedulers.computation())
                .materialize()
                .filter{!it.isOnError}
                .dematerialize { data->data}
    }
    fun downloadInfoCompanyById(id: Int): Single<CompanyDetail> {
        Timber.i("listenChange run");
        return api.getCompanyDetail(id.toString())
    }
}

