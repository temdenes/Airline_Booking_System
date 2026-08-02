package ch.tednes.airlinebooksys.infrastructure.entity.flights

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class AirportEntity(
    @Id
    @Column(unique = true, length = 3)
    var iataCode: String,
    var name: String,
    @ManyToOne
    var city: CityEntity,
)
