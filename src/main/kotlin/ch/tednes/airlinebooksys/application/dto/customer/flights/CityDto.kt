package ch.tednes.airlinebooksys.application.dto.customer.flights

data class CityDto(
    val name: String,
    var airports: List<AirportDto>
)
