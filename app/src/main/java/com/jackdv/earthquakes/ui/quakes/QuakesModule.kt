package com.jackdv.earthquakes.ui.quakes

import com.jackdv.earthquakes.di.scopes.ActivityScoped
import com.jackdv.earthquakes.di.scopes.FragmentScoped
import com.jackdv.earthquakes.ui.quakes.QuakesModule.QuakesAbstractModule
import com.jackdv.earthquakes.util.providers.BaseResourceProvider
import com.jackdv.earthquakes.util.providers.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [QuakesAbstractModule::class])
class QuakesModule {
    @ActivityScoped
    @Provides
    fun provideResourceProvider(context: QuakesActivity?): BaseResourceProvider {
        return ResourceProvider(context!!)
    }

    @Module
    interface QuakesAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun quakesFragment(): QuakesFragment?
    }
}