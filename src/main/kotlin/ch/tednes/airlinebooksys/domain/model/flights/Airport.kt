package ch.tednes.airlinebooksys.domain.model.flights

data class Airport(
    val iataCode: String,
    val name: String,
    val city: City,
)
