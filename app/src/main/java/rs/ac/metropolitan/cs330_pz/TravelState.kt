package rs.ac.metropolitan.cs330_pz

import androidx.compose.runtime.mutableStateOf

data class TravelState(
    val travels: List<Travel> = emptyList(),
    val travelFrom: String = "",
    val travelTo: String = "",
    val stops: String = "",
    var date: String = "",
    val distance: String = ""
)