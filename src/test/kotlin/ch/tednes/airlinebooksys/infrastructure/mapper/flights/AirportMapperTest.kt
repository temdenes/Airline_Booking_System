package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.application.dto.customer.flights.AirportDto
import ch.tednes.airlinebooksys.domain.model.flights.Airport
import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.infrastructure.entity.flights.AirportEntity
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CityEntity
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AirportMapperTest {

    private val testDataDomain = Airport(
        iataCode = "ZRH",
        name = "Zurich Airport",
        city = City(
            name = "Zurich",
            country = Country(
                name = "Switzerland",
                countryCode = "CH"
            )
        ),
    )

    private val testEntityData = AirportEntity(
        iataCode = "ZRH",
        name = "Zurich Airport",
        city = CityEntity(
            name = "Zurich",
            country = CountryEntity(
                name = "Switzerland",
                countryCode = "CH"
            )
        ),
    )

    private val testDtoData = AirportDto(
        iataCode = "ZRH",
        airportName = "Zurich Airport"
    )

    @Test
    fun `Convert from Domain Model to Entity`() {
        val res = testDataDomain.toEntity()
        assertEquals(testEntityData.iataCode, res.iataCode)
        assertEquals(testEntityData.name, res.name)
        assertEquals(testEntityData.city.name, res.city.name)
        assertEquals(testEntityData.city.country.name, res.city.country.name)
        assertEquals(testEntityData.city.country.countryCode, res.city.country.countryCode)
        assertEquals(testEntityData.city.id, res.city.id)
    }

    @Test
    fun `Convert from Entity to Domain Model`() {
        val res = testEntityData.toDomain()
        assertEquals(testDataDomain.iataCode, res.iataCode)
        assertEquals(testDataDomain.name, res.name)
        assertEquals(testDataDomain.city, res.city)
    }

    @Test
    fun `Convert from Domain Model to DTO`() {
        val res = testDataDomain.toDto()
        assertEquals(testDtoData.iataCode, res.iataCode)
        assertEquals(testDtoData.airportName, res.airportName)
    }
}