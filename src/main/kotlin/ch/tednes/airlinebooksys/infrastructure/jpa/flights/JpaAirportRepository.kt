package ch.tednes.airlinebooksys.infrastructure.jpa.flights

import ch.tednes.airlinebooksys.infrastructure.entity.flights.AirportEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaAirportRepository : JpaRepository<AirportEntity, String> {
}
