package com.example.rickandmorty20.domain.entity

import com.example.rickandmorty20.R

enum class CharacterGenderEntity(val resourceId: Int) {
    Female(R.string.gender_female),
    Male(R.string.gender_male),
    Genderless(R.string.gender_genderless),
    Unknown(R.string.gender_unknown),
}
