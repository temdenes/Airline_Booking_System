package ch.tednes.airlinebooksys.domain.service.customer.flight

import ch.tednes.airlinebooksys.application.dto.customer.flights.AirportDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.CityDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.CountryDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightSearchRequestDto
import ch.tednes.airlinebooksys.domain.repository.flights.AirportsRepository
import ch.tednes.airlinebooksys.domain.repository.flights.FlightsRepository
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toDto
import org.springframework.stereotype.Service

@Service
class FlightServiceCustomer(
    private val flightsRepo: FlightsRepository,
    private val airportsRepo: AirportsRepository
) {
    fun searchFlights(flightSearchRequestDto: FlightSearchRequestDto): List<FlightDto> {
        return flightsRepo.findAllByDepartureAndDestinationAndDate(
            departureAirportIataCode = flightSearchRequestDto.departureAirportIataCode,
            destinationAirportIataCode = flightSearchRequestDto.arrivalAirportIataCode,
            date = flightSearchRequestDto.departureDate
        ).map { it.toDto() }
    }

    fun getAllAirports(): List<CountryDto> {
        val countriesDto = ArrayList<CountryDto>()
        airportsRepo.findAll().forEach { airport ->
            val existingCountry = countriesDto.find { it.countryCode == airport.city.country.countryCode }
            if (existingCountry != null) {
                val existingCity = existingCountry.cities.find { it.name == airport.city.name }
                if (existingCity != null) {
                    existingCity.airports += AirportDto(
                        iataCode = airport.iataCode,
                        airportName = airport.name
                    )
                } else {
                    val city = CityDto(
                        name = airport.city.name,
                        airports = listOf(AirportDto(
                            iataCode = airport.iataCode,
                            airportName = airport.name
                        ))
                    )
                    existingCountry.cities += city
                }
            } else {
                val country = CountryDto(
                    countryCode = airport.city.country.countryCode,
                    countryName = airport.city.country.name,
                    cities = listOf(CityDto(
                        name = airport.city.name,
                        airports = listOf(
                            AirportDto(
                                iataCode = airport.iataCode,
                                airportName = airport.name
                            )
                        )
                    ))
                )
                countriesDto.add(country)
            }
        }
        return countriesDto
    }
}