package com.example.directorycompanies.di.modules

import com.example.directorycompanies.repository.network.ApiService
import com.example.directorycompanies.repository.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun api(retrofit: Retrofit):ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Named("endpoint")
    @Provides
    fun endpoint():String {
        return "https://lifehack.studio/test_task/"
    }
    @Provides
    fun retrofit(@Named("endpoint") baseUrl:String,
                 client: OkHttpClient,
                 jsonConverterFactory : GsonConverterFactory,
                 addCallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(jsonConverterFactory)
            .addCallAdapterFactory(addCallAdapterFactory)
            .baseUrl(baseUrl)
            .client(client)
            .build();
    }
    @Provides
    fun jsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    @Provides
    fun addCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
    @Provides
    fun httploggingInterceptor(): HttpLoggingInterceptor {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(object :
            HttpLoggingInterceptor.Logger {
            override fun log (message: String) {
                Timber.tag("httpClient").d(message);
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY;
        return interceptor
    }
    @Provides
    fun networkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                     networkInterceptor: NetworkInterceptor  ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();
    }
}