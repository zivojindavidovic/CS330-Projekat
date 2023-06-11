package rs.ac.metropolitan.cs330_pz

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_pz.database.TravelDao
import rs.ac.metropolitan.cs330_pz.database.TravelDatabase
import rs.ac.metropolitan.cs330_pz.model.Travel

@RunWith(AndroidJUnit4::class)
class TravelTest {
    private lateinit var travelDatabase: TravelDatabase
    private lateinit var dao: TravelDao

    @Before
    fun setUp(){
        travelDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            TravelDatabase::class.java
        ).build()

        dao = travelDatabase.dao
    }

    @After
    fun closeDb(){
        travelDatabase.close()
    }

    @Test
    fun writeAndRead() = runBlocking {
        val travel = Travel("Belgrade", "Zagreb", "Stop1, Stop2, Stop3", "TestDate", "300KM")

        dao.upsertTravel(travel)
        val travels = dao.getAllTravels()
        TestCase.assertTrue(travels.toList().isNotEmpty())
        TestCase.assertEquals(1, travels.toList().size)

    }
}