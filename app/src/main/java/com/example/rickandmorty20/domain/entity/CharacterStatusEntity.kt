package com.example.rickandmorty20.domain.entity

import com.example.rickandmorty20.R


enum class CharacterStatusEntity(val resourceId: Int) {
    Alive(R.string.status_alive), Dead(R.string.status_dead), Unknown(R.string.status_unknown),
}
