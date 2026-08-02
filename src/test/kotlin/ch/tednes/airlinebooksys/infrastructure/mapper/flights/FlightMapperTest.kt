package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.domain.model.flights.Airport
import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.domain.model.flights.Flight
import ch.tednes.airlinebooksys.infrastructure.entity.flights.FlightEntity
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class FlightMapperTest {
    private val departureAirportDomainModel = Airport(
        iataCode = "ZRH",
        name = "Zurich Airport",
        city = City(
            name = "Zurich",
            country = Country(
                name = "Switzerland",
                countryCode = "CH"
            )
        )
    )

    private val arrivalAirportDomainModel = Airport(
        iataCode = "JFK",
        name = "John F. Kennedy International Airport",
        city = City(
            name = "New York",
            country = Country(
                name = "United States",
                countryCode = "US"
            )
        )
    )

    private val now = Instant.now()

    private val testDomainFlight = Flight(
        flightNumber = "1234",
        departureAirport = departureAirportDomainModel,
        arrivalAirport = arrivalAirportDomainModel,
        departureDateTime = now,
        arrivalDateTime = now,
        distanceMiles = 1000,
    )

    private val testEntityFlight = FlightEntity(
        flightNumber = "1234",
        departureAirport = departureAirportDomainModel.toEntity(),
        arrivalAirport = arrivalAirportDomainModel.toEntity(),
        departureDateTime = now,
        arrivalDateTime = now,
        distanceMiles = 1000,
    )

    @Test
    fun `Convert from Domain Model to Entity`() {
        val res = testDomainFlight.toEntity()
        assertEquals(testEntityFlight.flightNumber, res.flightNumber)
        assertEquals(testEntityFlight.departureAirport.iataCode, res.departureAirport.iataCode)
        assertEquals(testEntityFlight.arrivalAirport.iataCode, res.arrivalAirport.iataCode)
        assertEquals(testEntityFlight.departureDateTime, res.departureDateTime)
        assertEquals(testEntityFlight.arrivalDateTime, res.arrivalDateTime)
        assertEquals(testEntityFlight.distanceMiles, res.distanceMiles)
    }

    @Test
    fun `Convert from Entity to Domain Model`() {
        val res = testEntityFlight.toDomain()
        assertEquals(testDomainFlight, res)
    }

}