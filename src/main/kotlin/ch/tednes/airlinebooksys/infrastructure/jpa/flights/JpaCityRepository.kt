package ch.tednes.airlinebooksys.infrastructure.jpa.flights

interface JpaCityRepository : org.springframework.data.jpa.repository.JpaRepository<ch.tednes.airlinebooksys.infrastructure.entity.flights.CityEntity, java.util.UUID> {
}
