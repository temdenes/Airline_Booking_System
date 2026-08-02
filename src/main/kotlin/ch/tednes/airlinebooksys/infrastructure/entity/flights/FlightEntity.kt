package ch.tednes.airlinebooksys.infrastructure.entity.flights

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
class FlightEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var flightNumber: String,
    @ManyToOne
    var departureAirport: AirportEntity,
    @ManyToOne
    var arrivalAirport: AirportEntity,
    var departureDateTime: Instant,
    var arrivalDateTime: Instant,
    var distanceMiles: Int
)
