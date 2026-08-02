package ch.tednes.airlinebooksys.infrastructure.mapper.flights

import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity
import kotlin.test.Test
import kotlin.test.assertEquals

class CountryMapperTest {
    private val testCountryDomain = Country(
        name = "Switzerland",
        countryCode = "CH"
    )

    private val testCountryEntity = CountryEntity(
        name = "Switzerland",
        countryCode = "CH"
    )

    @Test
    fun `Convert from Domain Model to Entity`() {
        val res = testCountryDomain.toEntity()
        assertEquals(testCountryEntity.name, res.name)
        assertEquals(testCountryEntity.countryCode, res.countryCode)
    }

    @Test
    fun `Convert from Entity to Domain Model`() {
        val res = testCountryEntity.toDomain()
        assertEquals(testCountryDomain.name, res.name)
        assertEquals(testCountryDomain.countryCode, res.countryCode)
    }
}