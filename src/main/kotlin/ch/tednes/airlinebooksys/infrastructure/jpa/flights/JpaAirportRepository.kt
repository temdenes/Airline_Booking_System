package ch.tednes.airlinebooksys.infrastructure.jpa.flights

interface AirportRepository : org.springframework.data.jpa.repository.JpaRepository<ch.tednes.airlinebooksys.infrastructure.entity.flights.AirportEntity, kotlin.String> {
}
