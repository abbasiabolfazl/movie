package me.akay.movies.characterList

import me.akay.movies.datamodels.Character

interface CharacterAdapterDelegate {
    fun onClicked(station: Character)
}