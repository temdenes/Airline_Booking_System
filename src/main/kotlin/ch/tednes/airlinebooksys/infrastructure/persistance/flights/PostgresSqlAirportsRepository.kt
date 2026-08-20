package ch.tednes.airlinebooksys.infrastructure.persistance.flights

import ch.tednes.airlinebooksys.domain.model.flights.Airport
import ch.tednes.airlinebooksys.domain.repository.flights.AirportsRepository
import ch.tednes.airlinebooksys.infrastructure.jpa.flights.JpaAirportRepository
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toDomain
import ch.tednes.airlinebooksys.infrastructure.mapper.flights.toEntity
import org.springframework.stereotype.Repository

@Repository
class PostgresSqlAirportsRepository(
    private val jpaRepo: JpaAirportRepository
) : AirportsRepository {
    override fun findAll(): List<Airport> {
        return jpaRepo.findAll().map { it.toDomain() }
    }

    override fun findByCode(code: String): Airport? {
        return jpaRepo.findById(code).orElse(null)?.toDomain()
    }

    override fun save(airport: Airport): Airport {
        return jpaRepo.save(airport.toEntity()).toDomain()
    }

    override fun deleteByCode(code: String) {
        return jpaRepo.deleteById(code)
    }
}