package rs.ac.metropolitan.cs330_pz

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_pz.screens.HomeScreen
import rs.ac.metropolitan.cs330_pz.screens.MapScreen
import rs.ac.metropolitan.cs330_pz.screens.TravelScreen

@Composable
fun NavSettup(navController: NavHostController){
    val vm: AppViewModel = viewModel()
    vm.navController = navController
    NavHost(navController = navController, startDestination = TravelRoute.Home.route ){
        composable(route = TravelRoute.Home.route){
            HomeScreen(vm)
        }
        composable(route = TravelRoute.AddTravel.route){
            TravelScreen()
        }
        composable(route = TravelRoute.Map.route){
            MapScreen()
        }
    }
}