package ch.tednes.airlinebooksys.infrastructure.mapper.util

import com.google.type.Date
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class GoogleGrpcDateMapperTest {
    private val testData = Date.newBuilder()
        .setYear(2021)
        .setMonth(1)
        .setDay(1)
        .build()
    private val expectedDate = LocalDate.of(2021, 1, 1)

    @Test
    fun `Test gRPC Date to LocalDate Converter is convert correctly`(){
        val date = testData.toGrpcLocalDate()
        assertEquals(expectedDate, date)
    }
}