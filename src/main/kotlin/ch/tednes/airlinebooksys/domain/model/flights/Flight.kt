package ch.tednes.airlinebooksys.domain.model.flights

import java.time.Instant
import java.util.*

data class Flight(
    val id: UUID? = null,
    val flightNumber: String,
    val departureAirport: Airport,
    val arrivalAirport: Airport,
    val departureDateTime: Instant,
    val arrivalDateTime: Instant,
    val distanceMiles: Int
)
