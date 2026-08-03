package ch.tednes.airlinebooksys.infrastructure.mapper.util

import com.google.type.Date
import java.time.LocalDate

fun Date.toGrpcLocalDate(): LocalDate {
    return LocalDate.of(this.year, this.month, this.day)
}