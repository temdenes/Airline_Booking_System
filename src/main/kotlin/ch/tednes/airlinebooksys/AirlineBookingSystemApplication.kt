package ch.tednes.airlinebooksys

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AirlineBookingSystemApplication

fun main(args: Array<String>) {
    runApplication<AirlineBookingSystemApplication>(*args)
}
