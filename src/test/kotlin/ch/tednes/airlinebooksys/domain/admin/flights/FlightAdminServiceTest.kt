package ch.tednes.airlinebooksys.domain.admin.flights

import ch.tednes.airlinebooksys.application.dto.admin.flights.CountryUpdateRequestDto
import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.domain.repository.flights.AirportsRepository
import ch.tednes.airlinebooksys.domain.repository.flights.CitiesRepository
import ch.tednes.airlinebooksys.domain.repository.flights.CountriesRepository
import ch.tednes.airlinebooksys.domain.repository.flights.FlightsRepository
import ch.tednes.airlinebooksys.domain.service.admin.flights.FlightAdminService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class FlightAdminServiceTest {
    private val flightsRepo: FlightsRepository = mockk()
    private val countriesRepo: CountriesRepository = mockk()
    private val citiesRepo: CitiesRepository = mockk()
    private val airportsRepo: AirportsRepository = mockk()

    private val flightAdminService = FlightAdminService(
        flightsRepository = flightsRepo,
        airportsRepository = airportsRepo,
        countriesRepository = countriesRepo,
        citiesRepository = citiesRepo
    )

    private val expectedCountries = listOf(
        Country(
            countryCode = "CH",
            name = "Switzerland"
        ),
        Country(
            countryCode = "US",
            name = "United States"
        )
    )

    @Test
    fun `Get All Countries Admin Service`() {
        every { countriesRepo.findAll() } returns expectedCountries

        val actualCountries = flightAdminService.getAllCountries()

        assertEquals(expectedCountries, actualCountries)
        verify(exactly = 1) { countriesRepo.findAll() }
    }

    @Test
    fun `Save New Country Admin Service - Success`() {
        val newCountry = Country(
            countryCode = "DE",
            name = "Germany"
        )

        every { countriesRepo.isExist(newCountry.countryCode) } returns false
        every { countriesRepo.save(newCountry) } returns newCountry

        val response = flightAdminService.saveNewCountry(newCountry)

        assertEquals(true, response.success)
        assertEquals("Country with code ${newCountry.countryCode} saved successfully.", response.message)
        verify(exactly = 1) { countriesRepo.isExist(newCountry.countryCode) }
        verify(exactly = 1) { countriesRepo.save(newCountry) }
    }

    @Test
    fun `Save New Country Admin Service - Failure`() {
        val newCountry = Country(
            countryCode = "DE",
            name = "Germany"
        )

        every { countriesRepo.isExist(newCountry.countryCode) } returns true
        every { countriesRepo.save(newCountry) } returns newCountry
        val response = flightAdminService.saveNewCountry(newCountry)
        assertEquals(false, response.success)
    }

    @Test
    fun `Update Country Admin Service - Success`() {
        val oldCountry = Country(
            countryCode = "UK",
            name = "United Kingdom"
        )
        val newCountry = Country(
            countryCode = "DE",
            name = "Germany"
        )

        every { countriesRepo.findByCode(oldCountry.countryCode) } returns oldCountry
        every { countriesRepo.save(newCountry) } returns newCountry

        val response = flightAdminService.updateCountry(
            body = CountryUpdateRequestDto(
                oldCountryCode = oldCountry.countryCode,
                newCountry = newCountry
            )
        )
        assertEquals(true, response.success)
        assertEquals("Country with code ${newCountry.countryCode} updated successfully.", response.message)
    }

    @Test
    fun `Update Country Admin Service - Failure`() {
        val oldCountry = Country(
            countryCode = "UK",
            name = "United Kingdom"
        )
        val newCountry = Country(
            countryCode = "DE",
            name = "Germany"
        )

        every { countriesRepo.findByCode(oldCountry.countryCode) } returns oldCountry
        every { countriesRepo.save(newCountry) } returns oldCountry

        val response = flightAdminService.updateCountry(
            body = CountryUpdateRequestDto(
                oldCountryCode = oldCountry.countryCode,
                newCountry = newCountry
            )
        )

        assertEquals(false, response.success)
    }
}