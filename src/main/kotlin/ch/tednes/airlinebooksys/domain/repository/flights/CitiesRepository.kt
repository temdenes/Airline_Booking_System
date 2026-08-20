package ch.tednes.airlinebooksys.domain.repository.flights

import ch.tednes.airlinebooksys.application.dto.admin.flights.AdminCityDto
import ch.tednes.airlinebooksys.domain.model.flights.City
import java.util.UUID

interface CitiesRepository {
    fun findAll(): List<City>
    fun findAllByCountryCodeAndName(countryCode: String, name: String): List<City>
    fun findAllByCountryCode(countryCode: String): List<City>
    fun save(city: City) : City
    fun deleteById(id: UUID)
    fun findById(id: UUID) : City?
}