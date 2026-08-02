package ch.tednes.airlinebooksys.infrastructure.entity.flights

import jakarta.persistence.*
import java.util.*

@Entity
class CityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var name: String,
    @ManyToOne
    var country: CountryEntity
)
