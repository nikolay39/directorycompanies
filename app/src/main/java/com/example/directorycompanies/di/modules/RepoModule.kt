package com.example.directorycompanies.di.modules

import com.example.directorycompanies.di.modules.ApiModule
import com.example.directorycompanies.repository.CompanyRepository
import com.example.directorycompanies.repository.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module(includes = [ApiModule::class])
class RepoModule {
    @Singleton
    @Provides
    fun quotesRepo(api: ApiService): CompanyRepository {
        return CompanyRepository(api);
    }
}