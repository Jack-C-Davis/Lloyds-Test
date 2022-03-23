package com.jackdv.earthquakes.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.jackdv.earthquakes.di.scopes.AppScoped
import com.jackdv.earthquakes.util.chromeTabsUtils.ChromeTabsWrapper
import com.jackdv.earthquakes.util.ConnectivityUtils.DefaultOnlineChecker
import com.jackdv.earthquakes.util.ConnectivityUtils.OnlineChecker
import com.jackdv.earthquakes.util.schedulers.BaseSchedulerProvider
import com.jackdv.earthquakes.util.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {
    @Provides
    @AppScoped
    fun provideConnectivityManager(context: Application): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @AppScoped
    @Provides
    fun providesChromeTabsWrapper(app: Application): ChromeTabsWrapper {
        return ChromeTabsWrapper(app.applicationContext)
    }

    @Provides
    @AppScoped
    fun onlineChecker(cm: ConnectivityManager): OnlineChecker {
        return DefaultOnlineChecker(cm)
    }

    @AppScoped
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider.instance
    }
}