package rs.ac.metropolitan.cs330_pz

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_pz.screens.AddTravelScreen
import rs.ac.metropolitan.cs330_pz.screens.HomeScreen
import rs.ac.metropolitan.cs330_pz.screens.MapScreen
import rs.ac.metropolitan.cs330_pz.screens.OpenAIScreen

@Composable
fun NavSettup(navController: NavHostController, context: Context, state: TravelState, onEvent: (TravelEvent) -> Unit){
    val vm: AppViewModel = viewModel()
    vm.navController = navController
    NavHost(navController = navController, startDestination = TravelRoute.Home.route ){
        composable(route = TravelRoute.Home.route){
            HomeScreen(vm)
        }
        composable(route = TravelRoute.AddTravel.route){
            AddTravelScreen(vm, state, onEvent)
        }
        composable(route = TravelRoute.Map.route){
            MapScreen()
        }
        composable(route = TravelRoute.OpenAI.route){
            OpenAIScreen(context = context, vm)
        }
    }
}