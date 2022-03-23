package com.jackdv.earthquakes.data.source

import com.jackdv.earthquakes.data.models.Quake
import io.reactivex.Single

interface QuakesDataSource {
    fun getQuakes(): Single<List<Quake>>
    fun getQuake(quakeId: String): Single<Quake>
    fun saveQuakes(quakes: List<Quake>)
    fun saveQuake(quake: Quake)
    fun deleteAllQuakes()
    fun deleteQuake(quakeId: String)
}