package me.akay.movies.dependencyInjection

import android.content.Context
import dagger.Module
import dagger.Provides
import me.akay.movies.ApplicationLoader
import me.akay.movies.network.ApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class
    ]
)
class ApplicationModule {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideContext(application: ApplicationLoader): Context {
        return application.applicationContext
    }
}
