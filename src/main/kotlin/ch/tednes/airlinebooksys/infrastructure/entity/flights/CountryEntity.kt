package ch.tednes.airlinebooksys.infrastructure.entity.flights

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class CountryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var name: String,
    var countryCode: String
)
