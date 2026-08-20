package ch.tednes.airlinebooksys.application.dto.admin.flights

import ch.tednes.airlinebooksys.domain.model.flights.Country

data class CountryUpdateRequestDto(
    val oldCountryCode: String,
    val newCountry: Country
)
