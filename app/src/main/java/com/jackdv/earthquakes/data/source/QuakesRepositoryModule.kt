package com.jackdv.earthquakes.data.source

import com.jackdv.earthquakes.data.source.local.QuakesDao
import com.jackdv.earthquakes.data.source.local.QuakesLocalDataModule
import com.jackdv.earthquakes.data.source.local.QuakesLocalDataSource
import com.jackdv.earthquakes.data.source.remote.QuakesApiService
import com.jackdv.earthquakes.data.source.remote.QuakesRemoteDataModule
import com.jackdv.earthquakes.data.source.remote.QuakesRemoteDataSource
import com.jackdv.earthquakes.data.source.scopes.Local
import com.jackdv.earthquakes.data.source.scopes.Remote
import com.jackdv.earthquakes.di.scopes.AppScoped
import com.jackdv.earthquakes.util.schedulers.BaseSchedulerProvider
import dagger.Module
import dagger.Provides

@Module(includes = [QuakesLocalDataModule::class, QuakesRemoteDataModule::class])
class QuakesRepositoryModule {
    @Provides
    @Local
    @AppScoped
    fun provideQuakesLocalDataSource(
        quakesDao: QuakesDao?,
        schedulerProvider: BaseSchedulerProvider?
    ): QuakesDataSource {
        return QuakesLocalDataSource(quakesDao!!, schedulerProvider!!)
    }

    @Provides
    @Remote
    @AppScoped
    fun provideQuakesRemoteDataSource(apiService: QuakesApiService?): QuakesDataSource {
        return QuakesRemoteDataSource(apiService!!)
    }
}