package rs.ac.metropolitan.cs330_pz

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TravelDao {

    @Upsert
    suspend fun upsertTravel(travel: Travel)

    @Delete
    suspend fun deleteTravel(travel: Travel)

    @Query("SELECT * FROM Travel")
    fun getAllTravels(): Flow<List<Travel>>

}