package me.akay.movies.dependencyInjection

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.akay.movies.ApplicationLoader
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ApplicationModule::class]
)
interface ApplicationComponent : AndroidInjector<ApplicationLoader> {
    override fun inject(application: ApplicationLoader)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationLoader): Builder

        fun build(): ApplicationComponent
    }
}