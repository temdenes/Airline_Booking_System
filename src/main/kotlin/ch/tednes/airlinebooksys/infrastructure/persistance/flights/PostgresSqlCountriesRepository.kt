package ch.tednes.airlinebooksys.infrastructure.persistance.flights

import ch.tednes.airlinebooksys.domain.model.flights.Country
import ch.tednes.airlinebooksys.domain.repository.flights.CountriesRepository
import ch.tednes.airlinebooksys.infrastructure.jpa.flights.JpaCountryRepository
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toDomain
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toEntity
import org.springframework.stereotype.Repository

@Repository
class PostgresSqlCountriesRepository(
    private val jpaRepo: JpaCountryRepository
) : CountriesRepository {
    override fun findAll(): List<Country> {
        return jpaRepo.findAll().map { it.toDomain() }
    }

    override fun save(country: Country): Country {
        return jpaRepo.save(country.toEntity()).toDomain()
    }

    override fun isExist(countryCode: String): Boolean {
        return jpaRepo.existsById(countryCode)
    }

    override fun findByCode(countryCode: String): Country? {
        return jpaRepo.findById(countryCode).orElse(null)?.toDomain()
    }

    override fun deleteByCode(countryCode: String) {
        return jpaRepo.deleteById(countryCode)
    }
}