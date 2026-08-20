package ch.tednes.airlinebooksys.infrastructure.api.flights

import ch.tednes.airlinebooksys.application.dto.customer.flights.AirportDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.FlightSearchRequestDto
import ch.tednes.airlinebooksys.application.dto.customer.flights.PassengerDto
import ch.tednes.airlinebooksys.domain.model.bookings.PassengerType
import ch.tednes.airlinebooksys.domain.service.customer.flight.FlightServiceCustomer
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import tools.jackson.databind.ObjectMapper
import java.time.Instant
import java.time.LocalDate
import kotlin.test.Test

@WebMvcTest(FlightsCustomerController::class)
class FlightsCustomerControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var flightServiceCustomer: FlightServiceCustomer

    @TestConfiguration
    class TestConfig {
        @Bean
        fun flightServiceCustomer(): FlightServiceCustomer = mockk()
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    @WithMockUser
    fun `searchFlights should return a list of flights when valid criteria are provided`() {
        // Given
        val requestDto = FlightSearchRequestDto(
            departureAirportIataCode = "BUD",
            arrivalAirportIataCode = "ZRH",
            departureDate = LocalDate.of(2026, 8, 27),
            passengers = listOf(
                PassengerDto(count = 1, type = PassengerType.ADULT)
            )
        )

        val expectedFlights = listOf(
            FlightDto(
                departureAirportDto = AirportDto(iataCode = "BUD", airportName = "Budapest Liszt Ferenc"),
                arrivalAirportDto = AirportDto(iataCode = "ZRH", airportName = "Zurich Airport"),
                departureDateTime = Instant.parse("2026-08-27T10:00:00Z"),
                arrivalTime = Instant.parse("2026-08-27T11:45:00Z")
            )
        )

        // Mock the service response using Mockk's 'every' matching block
        every { flightServiceCustomer.searchFlights(flightSearchRequestDto = any()) } returns expectedFlights

        // When & Then
        mockMvc.post("/api/v1/flights/customer/search") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(requestDto)
            with(csrf())
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$") { isArray() }
            jsonPath("$[0].departureAirportDto.iataCode") { value("BUD") }
            jsonPath("$[0].arrivalAirportDto.iataCode") { value("ZRH") }
        }

        // Verify the exact interaction with the mock service
        verify(exactly = 1) {
            flightServiceCustomer.searchFlights(flightSearchRequestDto = requestDto)
        }
    }
}