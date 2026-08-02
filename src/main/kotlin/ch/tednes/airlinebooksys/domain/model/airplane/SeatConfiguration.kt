package ch.tednes.airlinebooksys.domain.model.airplane

import java.util.*

data class SeatConfiguration(
    val id: UUID?,
    val type: AirplaneType,

    val totalRows: Int,
    val columnsLayout: List<String>
)
