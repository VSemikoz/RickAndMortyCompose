package com.example.rickandmorty20.common.mock

import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.domain.entity.CharacterGenderEntity
import com.example.rickandmorty20.domain.entity.CharacterLocationEntity
import com.example.rickandmorty20.domain.entity.CharacterOriginEntity
import com.example.rickandmorty20.domain.entity.CharacterStatusEntity
import java.util.Calendar

object CharacterMock {
    val mockItem = CharacterEntity(
        name = "Sas Sasov",
        id = 0,
        gender = CharacterGenderEntity.Male,
        status = CharacterStatusEntity.Alive,
        created = Calendar.getInstance().time,
        url = "",
        type = "",
        species = "",
        episode = listOf(),
        image = "",
        origin = CharacterOriginEntity("origin origin origin origin origin", ""),
        location = CharacterLocationEntity("Location", "")
    )
}