package rs.ac.metropolitan.cs330_pz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.ac.metropolitan.cs330_pz.model.Travel

@Database(
    entities = [Travel::class],
    version = 1
)
abstract class TravelDatabase: RoomDatabase() {

    abstract val dao: TravelDao

}