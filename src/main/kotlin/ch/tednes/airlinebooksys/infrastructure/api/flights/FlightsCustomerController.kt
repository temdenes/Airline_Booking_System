package ch.tednes.airlinebooksys.infrastructure.api.flights

import ch.tednes.airlinebooksys.application.dto.customer.flights.CountryDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightSearchRequestDto
import ch.tednes.airlinebooksys.domain.service.customer.flight.FlightServiceCustomer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/flights/customer")
class FlightsCustomerController(
    private val flightServiceCustomer: FlightServiceCustomer
) {
    @GetMapping("/airports")
    fun getAllAirports(): List<CountryDto> = flightServiceCustomer.getAllAirports()

    @PostMapping("/search")
    fun searchFlights(
        @RequestBody request: FlightSearchRequestDto
    ) : List<FlightDto> {
        return flightServiceCustomer.searchFlights(flightSearchRequestDto = request)
    }
}