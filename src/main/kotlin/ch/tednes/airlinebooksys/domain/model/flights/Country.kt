package ch.tednes.airlinebooksys.domain.model.flights

import java.util.*

data class Country(
    val id: UUID? = null,
    val name: String,
    val countryCode: String
)
