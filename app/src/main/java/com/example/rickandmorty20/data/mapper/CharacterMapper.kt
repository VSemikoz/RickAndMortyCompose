package com.example.rickandmorty20.data.mapper

import com.example.rickandmorty20.data.models.CharacterData
import com.example.rickandmorty20.data.models.CharacterGenderData
import com.example.rickandmorty20.data.models.CharacterInfoData
import com.example.rickandmorty20.data.models.CharacterLocationData
import com.example.rickandmorty20.data.models.CharacterOriginData
import com.example.rickandmorty20.data.models.CharacterResultData
import com.example.rickandmorty20.data.models.CharacterStatusData
import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.domain.entity.CharacterGenderEntity
import com.example.rickandmorty20.domain.entity.CharacterInfoEntity
import com.example.rickandmorty20.domain.entity.CharacterLocationEntity
import com.example.rickandmorty20.domain.entity.CharacterOriginEntity
import com.example.rickandmorty20.domain.entity.CharacterResultEntity
import com.example.rickandmorty20.domain.entity.CharacterStatusEntity


//Result
fun CharacterResultData.intoDomain(): CharacterResultEntity {
    return CharacterResultEntity(
        characterList = this.characterList.map { it.intoDomain() },
        characterInfo = this.characterInfo.intoDomain()
    )
}

fun CharacterResultEntity.intoData(): CharacterResultData {
    return CharacterResultData(
        characterList = this.characterList.map { it.intoData() },
        characterInfo = this.characterInfo.intoData()
    )
}

//Character Info
fun CharacterInfoData.intoDomain(): CharacterInfoEntity {
    return CharacterInfoEntity(
        count = this.count,
        pages = this.pages,
        next = this.next,
        prev = this.prev,
    )
}

fun CharacterInfoEntity.intoData(): CharacterInfoData {
    return CharacterInfoData(
        count = this.count,
        pages = this.pages,
        next = this.next,
        prev = this.prev,
    )
}


//Character
fun CharacterData.intoDomain(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status.intoDomain(),
        species = this.species,
        type = this.type,
        gender =
        this.gender.intoDomain(),
        origin = this.origin.intoDomain(),
        location = this.location.intoDomain(),
        image = this.image,
        episode = this.episode,
        url = this.url,
        created = this.created,
    )
}


fun CharacterEntity.intoData(): CharacterData {
    return CharacterData(
        id = this.id,
        name = this.name,
        status = this.status.intoData(),
        species = this.species,
        type = this.type,
        gender =
        this.gender.intoData(),
        origin = this.origin.intoData(),
        location = this.location.intoData(),
        image = this.image,
        episode = this.episode,
        url = this.url,
        created = this.created,
    )
}


//Character Origin
fun CharacterOriginData.intoDomain(): CharacterOriginEntity {
    return CharacterOriginEntity(
        name = this.name,
        url = this.url,
    )
}


fun CharacterOriginEntity.intoData(): CharacterOriginData {
    return CharacterOriginData(
        name = this.name,
        url = this.url,
    )
}

//Character Location
fun CharacterLocationData.intoDomain(): CharacterLocationEntity {
    return CharacterLocationEntity(
        name = this.name,
        url = this.url,
    )
}

fun CharacterLocationEntity.intoData(): CharacterLocationData {
    return CharacterLocationData(
        name = this.name,
        url = this.url,
    )
}

//Character Status
fun CharacterStatusData.intoDomain(): CharacterStatusEntity {
    return when (this) {
        CharacterStatusData.Alive -> CharacterStatusEntity.Alive
        CharacterStatusData.Dead -> CharacterStatusEntity.Dead
        CharacterStatusData.Unknown -> CharacterStatusEntity.Unknown
    }
}

fun CharacterStatusEntity.intoData(): CharacterStatusData {
    return when (this) {
        CharacterStatusEntity.Alive -> CharacterStatusData.Alive
        CharacterStatusEntity.Dead -> CharacterStatusData.Dead
        CharacterStatusEntity.Unknown -> CharacterStatusData.Unknown
    }
}


//Character Gender
fun CharacterGenderData.intoDomain(): CharacterGenderEntity {
    return when (this) {
        CharacterGenderData.Female -> CharacterGenderEntity.Female
        CharacterGenderData.Male -> CharacterGenderEntity.Male
        CharacterGenderData.Genderless -> CharacterGenderEntity.Genderless
        CharacterGenderData.Unknown -> CharacterGenderEntity.Unknown
    }
}


fun CharacterGenderEntity.intoData(): CharacterGenderData {
    return when (this) {
        CharacterGenderEntity.Female -> CharacterGenderData.Female
        CharacterGenderEntity.Male -> CharacterGenderData.Male
        CharacterGenderEntity.Genderless -> CharacterGenderData.Genderless
        CharacterGenderEntity.Unknown -> CharacterGenderData.Unknown
    }
}

