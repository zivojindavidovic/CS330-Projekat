package rs.ac.metropolitan.cs330_pz

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class AppViewModel(): ViewModel() {

    lateinit var navController: NavHostController

    fun navigateToMapScreen(){
        navController.navigate(TravelRoute.Map.route)
    }

}