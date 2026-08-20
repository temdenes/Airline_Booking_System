package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.application.dto.customer.flights.AirportDto
import ch.tednes.airlinebooksys.domain.model.flights.Airport
import ch.tednes.airlinebooksys.infrastructure.entity.flights.AirportEntity

/**
 * Convert the Airport Model to DTO
 */
fun Airport.toDto(): AirportDto =
    AirportDto(
        iataCode = this.iataCode,
        airportName = this.name
    )

/**
 * Convert the Airport Model to the Entity
 */
fun Airport.toEntity(): AirportEntity =
    AirportEntity(
        iataCode = this.iataCode,
        name = this.name,
        city = this.city.toEntity()
    )

/**
 * Convert the Airport Entity to the Domain Model
 */
fun AirportEntity.toDomain(): Airport =
    Airport(
        iataCode = this.iataCode,
        name = this.name,
        city = this.city.toDomain()
    )