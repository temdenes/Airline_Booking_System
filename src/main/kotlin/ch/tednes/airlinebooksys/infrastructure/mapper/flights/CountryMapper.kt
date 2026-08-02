package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity

fun CountryEntity.toDomain(): Country =
    Country(
        id = this.id,
        name = this.name,
        countryCode = this.countryCode
    )

fun Country.toEntity(): CountryEntity =
    CountryEntity(
        id = this.id,
        name = this.name,
        countryCode = this.countryCode
    )