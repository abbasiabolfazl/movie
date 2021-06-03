package me.akay.movies.dependencyInjection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.akay.movies.characterList.CharacterListFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeCharacterListFragment(): CharacterListFragment

}