package ch.tednes.airlinebooksys.infrastructure.jpa.flights

interface JpaCountryRepository : org.springframework.data.jpa.repository.JpaRepository<ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity, java.util.UUID> {
}
