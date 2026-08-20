package ch.tednes.airlinebooksys.domain.repository.flights

import ch.tednes.airlinebooksys.domain.model.flights.Airport

interface AirportsRepository {
    fun findAll(): List<Airport>
    fun findByCode(code: String): Airport?
    fun save(airport: Airport): Airport
    fun deleteByCode(code: String)
}