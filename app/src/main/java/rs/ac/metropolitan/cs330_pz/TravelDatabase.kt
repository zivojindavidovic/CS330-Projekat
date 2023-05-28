package rs.ac.metropolitan.cs330_pz

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Travel::class],
    version = 1
)
abstract class TravelDatabase: RoomDatabase() {

    abstract val dao: TravelDao

}