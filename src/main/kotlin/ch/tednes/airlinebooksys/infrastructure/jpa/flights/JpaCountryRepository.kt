package ch.tednes.airlinebooksys.infrastructure.jpa.flights

import ch.tednes.airlinebooksys.infrastructure.entity.flights.CountryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaCountryRepository : JpaRepository<CountryEntity, UUID> {
}
