package ch.tednes.airlinebooksys.domain.model.airplane

data class Airplane(
    val registrationCode: String,
    val airplaneType: AirplaneType,
    val seatConfiguration: SeatConfiguration
)
