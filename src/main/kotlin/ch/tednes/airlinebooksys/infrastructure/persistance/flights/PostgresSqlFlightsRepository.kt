package ch.tednes.airlinebooksys.infrastructure.persistance.flights

import ch.tednes.airlinebooksys.domain.model.flights.Flight
import ch.tednes.airlinebooksys.domain.repository.flights.FlightsRepository
import ch.tednes.airlinebooksys.infrastructure.jpa.flights.JpaFlightsRepository
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toDomain
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toEntity
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.ZoneId

@Repository
class PostgresSqlFlightsRepository(
    private val jpaRepo: JpaFlightsRepository
) : FlightsRepository {
    override fun findAll(): List<Flight> {
        return jpaRepo.findAll().map { it.toDomain() }
    }

    override fun findAllByDepartureAndDestinationAndDate(
        departureAirportIataCode: String,
        destinationAirportIataCode: String,
        date: LocalDate
    ): List<Flight> {
        val zoneId = ZoneId.of("Europe/Budapest")

        val startOfDay = date.atStartOfDay(zoneId).toInstant()

        val endOfDay = date.plusDays(1).atStartOfDay(zoneId).toInstant()

        return jpaRepo.findAllByDepartureDateTimeBetweenAndArrivalAirport_IataCodeAndDepartureAirport_IataCode(
            departureDateTimeAfter = startOfDay,
            departureDateTimeBefore = endOfDay,
            arrivalAirportIataCode = destinationAirportIataCode,
            departureAirportIataCode = departureAirportIataCode
        ).map { it.toDomain() }
    }

    override fun saveFlight(flight: Flight): Flight {
        return jpaRepo.save(flight.toEntity()).toDomain()
    }

    override fun saveAllFlight(flights: List<Flight>): List<Flight> {
        return jpaRepo.saveAll(flights.map { it.toEntity() }).map { it.toDomain() }
    }
}