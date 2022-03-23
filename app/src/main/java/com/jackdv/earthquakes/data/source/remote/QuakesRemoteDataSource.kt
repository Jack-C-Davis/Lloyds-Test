package com.jackdv.earthquakes.data.source.remote

import com.jackdv.earthquakes.data.models.Quake
import com.jackdv.earthquakes.data.models.QuakeWrapper
import com.jackdv.earthquakes.data.models.QuakesResponse
import com.jackdv.earthquakes.data.source.QuakesDataSource
import com.jackdv.earthquakes.di.scopes.AppScoped
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

/**
 * Remote Data Source implementation
 */
@AppScoped
class QuakesRemoteDataSource @Inject constructor(private val mApiService: QuakesApiService) :
    QuakesDataSource {
    /**
     * Fresh items are retrieved from Remote API
     */
    override fun getQuakes(): Single<List<Quake>> {
        return mApiService.quakes
            .flatMap { response: QuakesResponse ->
                Observable.fromIterable(response.quakeWrapperList).toList()
            }
            .flatMap { wrappersResponse: List<QuakeWrapper>? ->
                Observable.fromIterable(wrappersResponse)
                    .map { wrapper: QuakeWrapper ->
                        wrapper.quake.timeStampAdded = System.currentTimeMillis()
                        wrapper.quake
                    }.toList()
            }
    }

    /**
     * These methods should be implemented when required
     * (e.g. when a cloud service is integrated)
     */
    override fun getQuake(quakeId: String): Single<Quake> {
        throw Exception("Not implemented")
    }

    override fun saveQuakes(quakes: List<Quake>) {}
    override fun saveQuake(quake: Quake) {}
    override fun deleteAllQuakes() {}
    override fun deleteQuake(quakeId: String) {}
}