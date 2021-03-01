package com.example.directorycompanies.repository.network

import com.example.directorycompanies.repository.network.entity.CompanyDetail
import com.example.directorycompanies.repository.network.entity.CompanyPreview
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
interface ApiService {
    @GET("test.php")
    fun getCompanesPrieview(): Flowable<List<CompanyPreview>>;

    @GET("test.php")
    fun getCompanyDetail(@Query("id") id:String): Single<CompanyDetail>;

}
