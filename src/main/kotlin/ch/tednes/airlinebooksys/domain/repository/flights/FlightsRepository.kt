package ch.tednes.airlinebooksys.domain.repository.flights

import ch.tednes.airlinebooksys.domain.model.flights.Flight
import java.time.LocalDate

interface FlightsRepository {
    fun findAll(): List<Flight>
    fun findAllByDepartureAndDestinationAndDate(
        departureAirportIataCode: String,
        destinationAirportIataCode: String,
        date: LocalDate
    ): List<Flight>

    fun saveFlight(flight: Flight): Flight
    fun saveAllFlight(flights: List<Flight>): List<Flight>
}
