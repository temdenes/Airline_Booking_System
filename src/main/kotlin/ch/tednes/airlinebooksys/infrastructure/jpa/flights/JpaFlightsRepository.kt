package ch.tednes.airlinebooksys.infrastructure.jpa.flights

import ch.tednes.airlinebooksys.infrastructure.entity.flights.FlightEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.*

@Repository
interface JpaFlightsRepository : JpaRepository<FlightEntity, UUID> {
    fun findAllByDepartureDateTimeBetweenAndArrivalAirport_IataCodeAndDepartureAirport_IataCode(
        departureDateTimeAfter: Instant,
        departureDateTimeBefore: Instant,
        arrivalAirportIataCode: String,
        departureAirportIataCode: String
    ): MutableList<FlightEntity>
}
