package ch.tednes.airlinebooksys.domain.repository.flights

import ch.tednes.airlinebooksys.domain.model.flights.Country

interface CountriesRepository {
    fun findAll(): List<Country>
    fun save(country: Country): Country
    fun isExist(countryCode: String): Boolean
    fun findByCode(countryCode: String): Country?
    fun deleteByCode(countryCode: String)
}