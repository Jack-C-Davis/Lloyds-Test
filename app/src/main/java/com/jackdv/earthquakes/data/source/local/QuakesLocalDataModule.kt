package com.jackdv.earthquakes.data.source.local

import android.app.Application
import androidx.room.Room
import com.jackdv.earthquakes.di.scopes.AppScoped
import com.jackdv.earthquakes.util.Constants
import dagger.Module
import dagger.Provides

@Module
class QuakesLocalDataModule {
    @AppScoped
    @Provides
    fun provideDb(context: Application): QuakesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            QuakesDatabase::class.java,
            Constants.QUAKES_ROOM_DB_STRING
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScoped
    @Provides
    fun provideQuakesDao(db: QuakesDatabase): QuakesDao {
        return db.quakesDao()
    }
}