package ch.tednes.airlinebooksys.domain.model.flights

import java.util.UUID

data class Airport(
    val id: UUID? = null,
    val code: String,
    val name: String,
    val city: City,
)
