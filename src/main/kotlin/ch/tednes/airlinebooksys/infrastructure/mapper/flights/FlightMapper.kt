package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightDto
import ch.tednes.airlinebooksys.domain.model.flights.Flight
import ch.tednes.airlinebooksys.infrastructure.entity.flights.FlightEntity

fun Flight.toEntity(): FlightEntity =
    FlightEntity(
        id = this.id,
        flightNumber = this.flightNumber,
        departureAirport = this.departureAirport.toEntity(),
        arrivalAirport = this.arrivalAirport.toEntity(),
        departureDateTime = this.departureDateTime,
        arrivalDateTime = this.arrivalDateTime,
        distanceMiles = this.distanceMiles
    )

fun FlightEntity.toDomain(): Flight =
    Flight(
        id = this.id,
        flightNumber = this.flightNumber,
        departureAirport = this.departureAirport.toDomain(),
        arrivalAirport = this.arrivalAirport.toDomain(),
        departureDateTime = this.departureDateTime,
        arrivalDateTime = this.arrivalDateTime,
        distanceMiles = this.distanceMiles
    )

fun Flight.toDto(): FlightDto =
    FlightDto(
        departureAirportDto = this.departureAirport.toDto(),
        arrivalAirportDto = this.arrivalAirport.toDto(),
        departureDateTime = this.departureDateTime,
        arrivalTime = this.arrivalDateTime
    )