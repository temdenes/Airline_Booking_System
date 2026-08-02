package ch.tednes.airlinebooksys.domain.model.flights

import java.util.UUID

data class City(
    val id: UUID? = null,
    val name: String,
    val country: Country
)
