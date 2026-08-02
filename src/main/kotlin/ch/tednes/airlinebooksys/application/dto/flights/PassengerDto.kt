package ch.tednes.airlinebooksys.application.dto.flights

import ch.tednes.airlinebooksys.domain.model.bookings.PassengerType

data class PassengerDto(
    val count: Int,
    val type: PassengerType
)
