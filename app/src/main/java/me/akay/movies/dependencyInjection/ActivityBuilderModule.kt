package me.akay.movies.dependencyInjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.akay.movies.MainActivity

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}