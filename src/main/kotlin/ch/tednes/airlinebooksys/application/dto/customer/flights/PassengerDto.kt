package ch.tednes.airlinebooksys.application.dto.customer.flights

import ch.tednes.airlinebooksys.domain.model.bookings.PassengerType

data class PassengerDto(
    val count: Int,
    val type: PassengerType
)
