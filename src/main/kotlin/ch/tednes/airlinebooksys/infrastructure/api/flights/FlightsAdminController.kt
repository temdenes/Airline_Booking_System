package ch.tednes.airlinebooksys.infrastructure.api.flights

import ch.tednes.airlinebooksys.application.dto.DefaultResponse
import ch.tednes.airlinebooksys.application.dto.admin.flights.AdminAirportDto
import ch.tednes.airlinebooksys.application.dto.admin.flights.AdminCityDto
import ch.tednes.airlinebooksys.application.dto.admin.flights.CityUpdateRequestDto
import ch.tednes.airlinebooksys.application.dto.admin.flights.CountryUpdateRequestDto
import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.domain.service.admin.flights.FlightAdminService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/flights/admin")
class FlightsAdminController(
    private val flightsAdminService: FlightAdminService
) {
    @GetMapping("/countries")
    fun getAllCountries(): List<Country> = flightsAdminService.getAllCountries()

    @PostMapping("/flights/save")
    fun saveNewCountry(
        @RequestBody country: Country
    ) : DefaultResponse {
        return flightsAdminService.saveNewCountry(country)
    }

    @PutMapping("/countries/update")
    fun updateCountry(
        @RequestBody body: CountryUpdateRequestDto
    ) : DefaultResponse {
        return flightsAdminService.updateCountry(body)
    }

    @DeleteMapping("/countries/delete")
    fun deleteCountry(
        @RequestBody countryCode: String
    ) : DefaultResponse {
        return flightsAdminService.deleteCountry(countryCode)
    }

    @GetMapping("/cities")
    fun getAllCities(): List<AdminCityDto> = flightsAdminService.getAllCities()

    @PostMapping("/cities/save")
    fun saveNewCity(
        @RequestBody city: AdminCityDto
    ) : DefaultResponse {
        return flightsAdminService.saveCity(city)
    }

    @PutMapping("/cities/update")
    fun updateCity(
        @RequestBody body: CityUpdateRequestDto
    ) : DefaultResponse {
        return flightsAdminService.updateCity(body)
    }

    @DeleteMapping("/cities/delete")
    fun deleteCity(
        @RequestBody city: AdminCityDto
    ) : DefaultResponse {
        return flightsAdminService.deleteCity(city)
    }

    @GetMapping("/airports")
    fun getAllAirport() : List<AdminAirportDto> = flightsAdminService.getAllAirport()

    @PostMapping("/airports/save")
    fun saveNewAirport(
        @RequestBody airport: AdminAirportDto
    ) : DefaultResponse {
        return flightsAdminService.saveAirport(airport)
    }

    @PutMapping("/airports/update")
    fun updateAirport(
        @RequestBody airport: AdminAirportDto
    ) : DefaultResponse {
        return flightsAdminService.updateAirport(airport)
    }

    @DeleteMapping("/airports/delete")
    fun deleteAirport(
        @RequestBody airport: AdminAirportDto
    ) : DefaultResponse {
        return flightsAdminService.deleteAirport(airport)
    }
}