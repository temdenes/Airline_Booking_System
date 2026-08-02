package ch.tednes.airlinebooksys.domain.model.flights

import java.time.Instant
import java.util.UUID

data class Flight(
    val id: UUID,
    val flightNumber: String,
    val departureAirport: Airport,
    val arrivalAirport: Airport,
    val departureDateTime: Instant,
    val arrivalDateTime: Instant,
    val distanceMiles: Int
)
