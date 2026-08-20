package ch.tednes.airlinebooksys.infrastructure.persistance.flights

import ch.tednes.airlinebooksys.domain.model.flights.City
import ch.tednes.airlinebooksys.domain.repository.flights.CitiesRepository
import ch.tednes.airlinebooksys.infrastructure.jpa.flights.JpaCityRepository
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toDomain
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PostgresSqlCitiesRepository(
    private val jpaRepo: JpaCityRepository
) : CitiesRepository {
    override fun findAll(): List<City> {
        return jpaRepo.findAll().map { it.toDomain() }
    }

    override fun findAllByCountryCodeAndName(
        countryCode: String,
        name: String
    ): List<City> {
        return jpaRepo.findAllByCountry_CountryCodeAndName(
            countryCountryCode = countryCode,
            name = name
        ).map { it.toDomain() }
    }

    override fun findAllByCountryCode(countryCode: String): List<City> {
        return jpaRepo.findAllByCountry_CountryCode(
            countryCountryCode = countryCode
        ).map { it.toDomain() }
    }

    override fun save(city: City): City {
        return jpaRepo.save(city.toEntity()).toDomain()
    }

    override fun deleteById(id: UUID) {
        return jpaRepo.deleteById(id)
    }

    override fun findById(id: UUID): City? {
        return jpaRepo.findByIdOrNull(id)?.toDomain()
    }
}