package ch.tednes.airlinebooksys.infrastructure.jpa.flights

interface Flight : org.springframework.data.jpa.repository.JpaRepository<ch.tednes.airlinebooksys.infrastructure.entity.flights.FlightEntity, java.util.UUID> {
}
