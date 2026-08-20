package ch.tednes.airlinebooksys.application.dto.customer.flights

data class CountryDto(
    val countryCode: String,
    val countryName: String,
    var cities: List<CityDto>
)
