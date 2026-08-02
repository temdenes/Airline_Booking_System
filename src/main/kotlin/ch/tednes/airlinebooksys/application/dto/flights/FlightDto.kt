package ch.tednes.airlinebooksys.application.dto.flights

import java.time.Instant

data class FlightDto(
    val departureAirportDto: AirportDto,
    val arrivalAirportDto: AirportDto,
    val departureDateTime: Instant,
    val arrivalTime: Instant,
)
