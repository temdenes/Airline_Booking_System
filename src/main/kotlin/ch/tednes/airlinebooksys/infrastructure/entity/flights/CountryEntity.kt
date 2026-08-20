package ch.tednes.airlinebooksys.infrastructure.entity.flights

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class CountryEntity(
    @Id
    @Column(name = "country_code", unique = true, nullable = false)
    var countryCode: String,
    var name: String
)
