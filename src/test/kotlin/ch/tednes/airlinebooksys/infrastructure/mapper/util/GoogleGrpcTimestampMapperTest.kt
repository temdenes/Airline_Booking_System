package ch.tednes.airlinebooksys.infrastructure.mapper.util

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.test.Test
import kotlin.test.assertEquals

class GoogleGrpcTimestampMapperTest {
    val expectedDateSeconds = Timestamp.newBuilder()
        .setSeconds(1633036800) // 2021-10-01T00:00:00Z
        .setNanos(0)
        .build()

    val testDataSeconds = Instant.ofEpochSecond(1633036800)

    val expectedNanos = Timestamp.newBuilder()
        .setSeconds(1633036800)
        .setNanos(123456789)
        .build()

    val testDataNanos = Instant.ofEpochSecond(1633036800, 123456789)

    @Test
    fun  `Test Instant to gRPC Timestamp Converter is convert correctly with seconds`(){
        val timestamp = testDataSeconds.toGrpcTimestamp()
        assertEquals(expectedDateSeconds, timestamp)
    }


    @Test
    fun  `Test Instant to gRPC Timestamp Converter is convert correctly with nanos`() {
        val timestamp = testDataNanos.toGrpcTimestamp()
        assertEquals(expectedNanos, timestamp)
    }
}