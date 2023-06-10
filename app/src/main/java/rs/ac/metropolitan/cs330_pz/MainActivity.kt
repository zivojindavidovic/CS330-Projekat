package rs.ac.metropolitan.cs330_pz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import rs.ac.metropolitan.cs330_pz.database.TravelDatabase
import rs.ac.metropolitan.cs330_pz.model.BottomNavItem
import rs.ac.metropolitan.cs330_pz.navigation.NavSettup
import rs.ac.metropolitan.cs330_pz.screens.navigation.BottomNavigation
import rs.ac.metropolitan.cs330_pz.ui.theme.CS330PZTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            TravelDatabase::class.java,
            "travel.db"
        ).build()
    }

    private val viewModel by viewModels <AppViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AppViewModel(db.dao) as T
                }
            }
        }
    )
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330PZTheme {
                navController = rememberNavController()
                val state by viewModel.state.collectAsState()
                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Map",
                                    route = "map",
                                    icon = Icons.Default.LocationOn
                                ),
                                BottomNavItem(
                                    name = "AI",
                                    route = "openai",
                                    icon = Icons.Default.Search
                                ),
                                BottomNavItem(
                                    name = "Add",
                                    route = "add",
                                    icon = Icons.Default.Add
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {  paddingValue->
                    NavSettup(navController = navController, state = state, context = this, onEvent = viewModel::onEvent, paddingValues = paddingValue)
                }
            }
        }
    }
}
