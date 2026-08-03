package ch.tednes.airlinebooksys.infrastructure.grpc.flights

import ch.tednes.airlinebooksys.application.dto.flights.FlightSearchRequestDto
import ch.tednes.airlinebooksys.application.dto.flights.PassengerDto
import ch.tednes.airlinebooksys.domain.model.bookings.PassengerType
import ch.tednes.airlinebooksys.domain.service.flight.FlightServiceCustomer
import ch.tednes.airlinebooksys.infrastructure.mapper.util.toGrpcLocalDate
import ch.tednes.airlinebooksys.infrastructure.mapper.util.toGrpcTimestamp
import ch.tednes.airlinebooksys.proto.Airport
import ch.tednes.airlinebooksys.proto.CustomerFlightsServiceGrpc
import ch.tednes.airlinebooksys.proto.FlightSearchResponse
import ch.tednes.airlinebooksys.proto.FlightsSearch
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

@Service
class GrpcFlightsCustomerService(
    private val customerFlightsService: FlightServiceCustomer
) : CustomerFlightsServiceGrpc.CustomerFlightsServiceImplBase() {
    override fun searchFlights(request: FlightsSearch?, responseObserver: StreamObserver<FlightSearchResponse?>?) {
        if (request == null) {
            responseObserver?.onError(NullPointerException("Request is null"))
        }
        customerFlightsService.searchFlights(
            flightSearchRequestDto = FlightSearchRequestDto(
                departureAirportIataCode = request!!.departureAirportIataCode,
                arrivalAirportIataCode = request.arrivalAirportIataCode,
                departureDate = request.departureDate.toGrpcLocalDate(),
                passengers = request.passengersList.map { passenger ->
                    PassengerDto(
                        count = passenger.count,
                        type = when (passenger.typeValue) {
                            1 -> PassengerType.CHILD
                            2 -> PassengerType.INFANT
                            else -> PassengerType.ADULT
                        }
                    )
                },
            )
        ).let { flightDtos ->
            flightDtos.map { flight ->
                ch.tednes.airlinebooksys.proto.Flight.newBuilder()
                    .setDepartureAirport(
                        Airport.newBuilder()
                            .setIataCode(flight.departureAirportDto.iataCode)
                            .setName(flight.departureAirportDto.airportName)
                            .build()
                    )
                    .setArrivalAirport(
                        Airport.newBuilder()
                            .setIataCode(flight.arrivalAirportDto.iataCode)
                            .setName(flight.arrivalAirportDto.airportName)
                            .build()
                    )
                    .setDepartureTime(flight.departureDateTime.toGrpcTimestamp())
                    .setArrivalTime(flight.departureDateTime.toGrpcTimestamp())
                    .build()
            }.let { flightBuilders ->
                val response = FlightSearchResponse.newBuilder()
                    .addAllFlights(flightBuilders)
                    .build()
                responseObserver?.onNext(response); responseObserver?.onCompleted()
            }
            responseObserver?.onError(NullPointerException("ResponseObserver is null"))
        }
        responseObserver?.onError(NullPointerException("ResponseObserver is null"))
    }
}