package ch.tednes.airlinebooksys.application.dto.admin.flights

data class AdminAirportDto(
    val iataCode: String,
    val airportName: String,
    val city: AdminCityDto,
)
