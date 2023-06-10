package rs.ac.metropolitan.cs330_pz.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_pz.AppViewModel
import rs.ac.metropolitan.cs330_pz.TravelEvent
import rs.ac.metropolitan.cs330_pz.TravelState
import rs.ac.metropolitan.cs330_pz.model.Travel
import rs.ac.metropolitan.cs330_pz.screens.AddTravelScreen
import rs.ac.metropolitan.cs330_pz.screens.HomeScreen
import rs.ac.metropolitan.cs330_pz.screens.MapScreen
import rs.ac.metropolitan.cs330_pz.screens.OpenAIScreen
import rs.ac.metropolitan.cs330_pz.screens.TravelScreen

@Composable
fun NavSettup(navController: NavHostController, context: Context, state: TravelState, onEvent: (TravelEvent) -> Unit, paddingValues: PaddingValues){
    val vm: AppViewModel = viewModel()
    vm.navController = navController
    NavHost(navController = navController, startDestination = TravelRoute.Home.route ){
        composable(route = TravelRoute.Home.route){
            HomeScreen(vm, state, onEvent)
        }
        composable(route = TravelRoute.AddTravel.route){
            AddTravelScreen(vm, state, onEvent)
        }
        composable(route = TravelRoute.Map.route){
            MapScreen(paddingValues = paddingValues)
        }
        composable(route = TravelRoute.OpenAI.route){
            OpenAIScreen(context = context, vm)
        }
        composable(route = TravelRoute.TravelDetails.route){ navBackStackEntry ->
            val travelId = navBackStackEntry.arguments?.getString("travelId")
            if(travelId != null){
                TravelScreen(vm, travelId.toInt(), state)
            }else{
                Toast.makeText(navController.context, "Error, travelId reqiored", Toast.LENGTH_LONG).show()
            }
        }
    }
}