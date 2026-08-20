package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity

fun CountryEntity.toDomain(): Country =
    Country(
        countryCode = this.countryCode,
        name = this.name
    )

fun Country.toEntity(): CountryEntity =
    CountryEntity(
        countryCode = this.countryCode,
        name = this.name
    )