package ch.tednes.airlinebooksys.application.dto.admin.flights

data class CityUpdateRequestDto(
    val oldCity: AdminCityDto,
    val newCity: AdminCityDto,
)
