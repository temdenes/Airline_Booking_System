package ch.tednes.airlinebooksys.domain.service.flight

import ch.tednes.airlinebooksys.application.dto.flights.FlightDto
import ch.tednes.airlinebooksys.application.dto.flights.FlightSearchRequestDto
import ch.tednes.airlinebooksys.domain.repository.flights.FlightsRepository
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toDto
import org.springframework.stereotype.Service

@Service
class FlightServiceCustomer(
    private val flightsRepo: FlightsRepository
) {

    fun searchFlights(flightSearchRequestDto: FlightSearchRequestDto): List<FlightDto> {
        return flightsRepo.findAllByDepartureAndDestinationAndDate(
            departureAirportIataCode = flightSearchRequestDto.departureAirportIataCode,
            destinationAirportIataCode = flightSearchRequestDto.arrivalAirportIataCode,
            date = flightSearchRequestDto.departureDate
        ).map { it.toDto() }
    }
}