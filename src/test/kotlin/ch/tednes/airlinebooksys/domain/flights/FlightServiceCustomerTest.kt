package ch.tednes.airlinebooksys.domain.flights

import ch.tednes.airlinebooksys.application.dto.flights.FlightDto
import ch.tednes.airlinebooksys.application.dto.flights.FlightSearchRequestDto
import ch.tednes.airlinebooksys.domain.model.flights.Flight // Assuming your entity package
import ch.tednes.airlinebooksys.domain.repository.flights.FlightsRepository
import ch.tednes.airlinebooksys.domain.service.flight.FlightServiceCustomer
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID.randomUUID

class FlightServiceCustomerTest {

    // 1. Create a mock instance of the repository
    private val flightsRepo: FlightsRepository = mockk()

    // 2. Inject the mock into the service
    private val flightService = FlightServiceCustomer(flightsRepo)

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `searchFlights should return mapped FlightDto list when flights match criteria`() {
        // Arrange
        val testDate = LocalDate.of(2026, 8, 15)
        val requestDto = FlightSearchRequestDto(
            departureAirportIataCode = "ZRH",
            arrivalAirportIataCode = "JFK",
            departureDate = testDate,
            passengers = emptyList()
        )
        val randomId = randomUUID()

        // Mock entity data (Adjust properties to match your actual Flight domain model)
        val mockFlightEntity = mockk<Flight>(relaxed = true).apply {
            every { id } returns randomId
            // You can add explicit stubs here if toDto() crashes on default relaxed values
        }

        val mockEntities = listOf(mockFlightEntity)

        every {
            flightsRepo.findAllByDepartureAndDestinationAndDate("ZRH", "JFK", testDate)
        } returns mockEntities

        // Act
        val result: List<FlightDto> = flightService.searchFlights(requestDto)

        // Assert
        assertEquals(1, result.size)
        verify(exactly = 1) {
            flightsRepo.findAllByDepartureAndDestinationAndDate("ZRH", "JFK", testDate)
        }
    }

    @Test
    fun `searchFlights should return empty list when no flights match criteria`() {
        // Arrange
        val testDate = LocalDate.of(2026, 8, 15)
        val requestDto = FlightSearchRequestDto("ZRH", "LAX", testDate, emptyList())

        every {
            flightsRepo.findAllByDepartureAndDestinationAndDate("ZRH", "LAX", testDate)
        } returns emptyList()

        // Act
        val result = flightService.searchFlights(requestDto)

        // Assert
        assertTrue(result.isEmpty())
        verify(exactly = 1) {
            flightsRepo.findAllByDepartureAndDestinationAndDate("ZRH", "LAX", testDate)
        }
    }
}
