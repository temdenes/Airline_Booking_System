package ch.tednes.airlinebooksys.infrastructure.mapper.util

import com.google.protobuf.Timestamp
import java.time.Instant

fun Instant.toGrpcTimestamp(): Timestamp {
    return Timestamp.newBuilder()
        .setSeconds(this.epochSecond)
        .setNanos(this.nano)
        .build()
}