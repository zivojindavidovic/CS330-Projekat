package rs.ac.metropolitan.cs330_pz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Travel(
    val travelFrom: String,
    val travelTo: String,
    val travelStops: String,
    val travelDate: String,
    val travelDistance: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)