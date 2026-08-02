package ch.tednes.airlinebooksys.domain.model.flights

import java.util.*

data class City(
    val id: UUID? = null,
    val name: String,
    val country: Country
)
