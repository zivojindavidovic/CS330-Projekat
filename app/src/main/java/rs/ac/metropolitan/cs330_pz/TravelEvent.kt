package rs.ac.metropolitan.cs330_pz

import rs.ac.metropolitan.cs330_pz.model.Travel

sealed interface TravelEvent {
    object SaveTravel: TravelEvent
    data class SetTravelFrom(val travelFrom: String): TravelEvent
    data class SetTravelTo(val travelTo: String): TravelEvent
    data class SetStops(val stops: String): TravelEvent
    data class SetTravelDate(val date: String): TravelEvent
    data class SetDistance(val distance: String): TravelEvent
    data class DeleteTravel(val travel: Travel): TravelEvent
}