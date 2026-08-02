package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CityEntity

fun City.toEntity(): CityEntity =
    CityEntity(
        id = this.id,
        name = this.name,
        country = this.country.toEntity()
    )

fun CityEntity.toDomain(): City =
    City(
        id = this.id,
        name = this.name,
        country = this.country.toDomain()
    )