package ch.tednes.airlinebooksys.domain.service.admin.flights

import ch.tednes.airlinebooksys.application.dto.DefaultResponse
import ch.tednes.airlinebooksys.application.dto.admin.flights.AdminAirportDto
import ch.tednes.airlinebooksys.application.dto.admin.flights.AdminCityDto
import ch.tednes.airlinebooksys.application.dto.admin.flights.CityUpdateRequestDto
import ch.tednes.airlinebooksys.application.dto.admin.flights.CountryUpdateRequestDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.AirportDto
import ch.tednes.airlinebooksys.domain.model.flights.Airport
import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.domain.repository.flights.AirportsRepository
import ch.tednes.airlinebooksys.domain.repository.flights.CitiesRepository
import ch.tednes.airlinebooksys.domain.repository.flights.CountriesRepository
import ch.tednes.airlinebooksys.domain.repository.flights.FlightsRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

/**
 * Service class for managing flights, airports, countries, and cities in the airline booking system.
 * @param flightsRepository Flight Repository for manage flights
 * @param airportsRepository Airport Repository for manage airports
 * @param countriesRepository Country Repository for manage countries
 * @param citiesRepository City Repository for manage cities
 */
@Service
class FlightAdminService(
    private val flightsRepository: FlightsRepository,
    private val airportsRepository: AirportsRepository,
    private val countriesRepository: CountriesRepository,
    private val citiesRepository: CitiesRepository,
) {
    /**
     * Get All Countries from DB
     * @return List of Country
     */
    fun getAllCountries(): List<Country> = countriesRepository.findAll()

    /**
     * Save new country
     * @param country Country to save
     * @return DefaultResponse with success or failure message
     */
    fun saveNewCountry(country: Country) : DefaultResponse {
        countriesRepository.isExist(countryCode = country.countryCode).let {
            if (it) {
                return DefaultResponse(
                    success = false,
                    message = "Country with code ${country.countryCode} already exists."
                )
            } else {
                countriesRepository.save(country)
                return DefaultResponse(
                    success = true,
                    message = "Country with code ${country.countryCode} saved successfully."
                )
            }
        }
    }

    /**
     * Update Country
     * @param body
     * @return DefaultResponse with success or failure message
     */
    fun updateCountry(body: CountryUpdateRequestDto): DefaultResponse {
    val existingCountry = countriesRepository.findByCode(body.oldCountryCode)
        ?: return DefaultResponse(
            success = false,
            message = "Country with code ${body.oldCountryCode} does not exist."
        )

    val savedCountry = countriesRepository.save(body.newCountry)
    return if (savedCountry == body.newCountry) {
        DefaultResponse(
            success = true,
            message = "Country with code ${body.newCountry.countryCode} updated successfully."
        )
    } else {
        DefaultResponse(
            success = false,
            message = "Country with code ${existingCountry.countryCode} could not be updated."
        )
    }
}

    /**
     * Delete Country
     * @param countryCode Country code to delete
     * @return DefaultResponse with success or failure message
     */
    fun deleteCountry(countryCode: String) : DefaultResponse {
        try {
            countriesRepository.deleteByCode(countryCode)
            return DefaultResponse(
                success = true,
                message = "Country with code $countryCode deleted successfully."
            )
        } catch (e: Exception) {
            return DefaultResponse(
                success = false,
                message = e.message ?: "Error deleting country"
            )
        }
    }

    fun getAllCities() : List<AdminCityDto> {
        return citiesRepository.findAll().map { city ->
            AdminCityDto(
                name = city.name,
                countryCode = city.country.countryCode
            )
        }
    }

    fun saveCity(adminCityDto: AdminCityDto): DefaultResponse {
        citiesRepository.findAllByCountryCodeAndName(
            countryCode = adminCityDto.countryCode,
            name = adminCityDto.name
        ).let {
            if (it.isNotEmpty()) {
                return DefaultResponse(
                    success = false,
                    message = "City with code ${it.first().name} already exists."
                )
            }
            val country = countriesRepository.findByCode(adminCityDto.countryCode)?.let { country ->
                citiesRepository.save(
                    City(
                        name = adminCityDto.name,
                        country = country
                    )
                )
                return DefaultResponse(
                    success = true,
                    message = "City with code ${adminCityDto.countryCode} saved successfully."
                )
            }
            return DefaultResponse(
                success = false,
                message = "City with code ${adminCityDto.countryCode} already exists."
            )
        }
    }

    fun updateCity(body: CityUpdateRequestDto): DefaultResponse {
        val newCity = body.newCity
        val oldCity = body.oldCity
        citiesRepository.findAllByCountryCodeAndName(
            countryCode = oldCity.countryCode,
            name = newCity.name
        ).let {
            if (it.isEmpty()) {
                return DefaultResponse(
                    success = false,
                    message = "City with name ${oldCity.name} does not exist."
                )
            }
            countriesRepository.findByCode(countryCode = newCity.countryCode)?.let { country ->
                citiesRepository.save(
                    it[0].copy(
                        name = newCity.name,
                        country = country
                    )
                )
                return DefaultResponse(
                    success = true,
                    message = "City with name ${newCity.name} updated successfully."
                )
            }
            return DefaultResponse(
                success = false,
                message = "Country with code ${newCity.countryCode} does not exist."
            )
        }
    }

    fun deleteCity(adminCityDto: AdminCityDto) : DefaultResponse {
        citiesRepository.findAllByCountryCodeAndName(
            countryCode = adminCityDto.countryCode,
            name = adminCityDto.name
        ).let {
            if (it.isEmpty() || it[0].id == null) {
                return DefaultResponse(
                    success = false,
                    message = "City with name ${adminCityDto.name} does not exist."
                )
            }
            citiesRepository.deleteById(it[0].id!!)
            return DefaultResponse(
                success = true,
                message = "City with name ${adminCityDto.name} deleted successfully."
            )
        }
    }

    fun getAllAirport() : List<AdminAirportDto> {
        return airportsRepository.findAll().map { airport ->
            AdminAirportDto(
                iataCode = airport.iataCode,
                airportName = airport.name,
                city = AdminCityDto(
                    name = airport.city.name,
                    countryCode = airport.city.country.countryCode
                )
            )
        }
    }


    fun saveAirport(airportDto: AdminAirportDto) : DefaultResponse {
        airportsRepository.findByCode(airportDto.iataCode)?.let {
            return DefaultResponse(
                success = false,
                message = "Airport with code ${airportDto.iataCode} already exists."
            )
        }
        val city = citiesRepository.findAllByCountryCode(airportDto.city.countryCode)
            .find { it.name == airportDto.city.name } ?:
            return DefaultResponse(
                success = false,
                message = "City with name ${airportDto.city.name} does not exist."
            )
        airportsRepository.save(
            Airport(
                iataCode = airportDto.iataCode,
                name = airportDto.airportName,
                city = city
            )
        )
        return DefaultResponse(
            success = true,
            message = "Airport with code ${airportDto.iataCode} saved successfully."
        )
    }

    fun updateAirport(airportDto: AdminAirportDto) : DefaultResponse {
        airportsRepository.findByCode(airportDto.iataCode)?.let { existingAirport ->
            val city = citiesRepository.findAllByCountryCode(airportDto.city.countryCode)
                .find { it.name == airportDto.city.name } ?:
                return DefaultResponse(
                    success = false,
                    message = "City with name ${airportDto.city.name} does not exist."
                )
            airportsRepository.save(
                existingAirport.copy(
                    name = airportDto.airportName,
                    city = city,
                )
            )
            return DefaultResponse(
                success = true,
                message = "Airport with code ${airportDto.iataCode} updated successfully."
            )
        }
        return DefaultResponse(
            success = false,
            message = "Airport with code ${airportDto.iataCode} does not exist."
        )
    }

    fun deleteAirport(airportDto: AdminAirportDto) : DefaultResponse {
        airportsRepository.findByCode(airportDto.iataCode)?.let { existingAirport ->
            try {
                airportsRepository.deleteByCode(existingAirport.iataCode)
            } catch (e: Exception) {
                return DefaultResponse(
                    success = false,
                    message = e.message ?: "Error deleting airport"
                )
            }
            return DefaultResponse(
                success = true,
                message = "Airport with code ${airportDto.iataCode} deleted successfully."
            )
        }
        return DefaultResponse(
            success = false,
            message = "Airport with code ${airportDto.iataCode} does not exist."
        )
    }


}