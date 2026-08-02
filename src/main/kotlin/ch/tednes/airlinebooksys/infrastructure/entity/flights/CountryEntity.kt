package ch.tednes.airlinebooksys.domain.model.flights

import java.util.UUID

data class Country(
    val id: UUID? = null,
    val name: String,
)
