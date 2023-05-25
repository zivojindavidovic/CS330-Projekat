package rs.ac.metropolitan.cs330_pz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_pz.screens.navigation.BottomNavigation
import rs.ac.metropolitan.cs330_pz.ui.theme.CS330PZTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330PZTheme {
                navController = rememberNavController()
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
                    NavSettup(navController = navController, context = this)
                }
            }
        }
    }
}
