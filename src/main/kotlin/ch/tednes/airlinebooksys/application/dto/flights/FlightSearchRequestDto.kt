package ch.tednes.airlinebooksys.application.dto.flights

import java.time.LocalDate

data class FlightSearchRequestDto(
    val departureAirportIataCode: String,
    val arrivalAirportIataCode: String,
    val departureDate: LocalDate,
    val passengers: List<PassengerDto>
)
