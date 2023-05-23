package rs.ac.metropolitan.cs330_pz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class AppViewModel(): ViewModel() {

    lateinit var navController: NavHostController
    var grantedInternetPermission = mutableStateOf(false)

    fun navigateToMapScreen(){
        navController.navigate(TravelRoute.Map.route)
    }

}