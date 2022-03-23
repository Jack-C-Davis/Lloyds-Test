package com.jackdv.earthquakes.data.source.remote

import retrofit2.http.GET
import com.jackdv.earthquakes.data.models.QuakesResponse
import io.reactivex.Single

interface QuakesApiService {
    @get:GET("1.0_day.geojson")
    val quakes: Single<QuakesResponse>
}