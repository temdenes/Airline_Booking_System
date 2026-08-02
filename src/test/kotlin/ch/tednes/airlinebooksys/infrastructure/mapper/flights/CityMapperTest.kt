package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CityEntity
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity
import kotlin.test.Test
import kotlin.test.assertEquals

class CityMapperTest {
    private val testCountryDomain = Country(
        name = "Switzerland",
        countryCode = "CH"
    )

    private val testCountryEntity = CountryEntity(
        name = "Switzerland",
        countryCode = "CH"
    )

    private val testDataDomain = City(
        name = "Zurich",
        country = testCountryDomain
    )

    private val testEntityData = CityEntity(
        name = "Zurich",
        country = testCountryEntity
    )

    @Test
    fun `Convert from Domain Model to Entity`() {
        val res = testDataDomain.toEntity()
        assertEquals(testEntityData.name, res.name)
        assertEquals(testEntityData.country.name, res.country.name)
        assertEquals(testEntityData.country.countryCode, res.country.countryCode)
        assertEquals(testEntityData.country.id, res.country.id)
    }

    @Test
    fun `Convert from Entity to Domain Model`() {
        val res = testEntityData.toDomain()
        assertEquals(testDataDomain.name, res.name)
        assertEquals(testDataDomain.country, res.country)
    }

}