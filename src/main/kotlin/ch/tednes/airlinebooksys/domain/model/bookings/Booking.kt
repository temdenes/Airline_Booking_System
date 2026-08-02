package ch.tednes.airlinebooksys.domain.model.bookings

import ch.tednes.airlinebooksys.domain.model.flights.Flight

data class Booking(
    val bookingNumber: String,
    val flight: Flight
)
