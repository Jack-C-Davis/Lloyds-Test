package com.jackdv.earthquakes.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jackdv.earthquakes.di.quakes.QuakesViewModelFactory
import com.jackdv.earthquakes.di.scopes.AppScoped
import com.jackdv.earthquakes.ui.quakes.QuakesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuakesViewModel::class)
    abstract fun bindQuakesViewModel(quakesViewModel: QuakesViewModel?): ViewModel?
    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: QuakesViewModelFactory?): ViewModelProvider.Factory?
}