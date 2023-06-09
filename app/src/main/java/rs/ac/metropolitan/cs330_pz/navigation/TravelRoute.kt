package rs.ac.metropolitan.cs330_pz.navigation

sealed class TravelRoute(val route: String) {

    object Home: TravelRoute(route = "home")
    object AddTravel: TravelRoute(route = "add")
    object Map: TravelRoute(route = "map")
    object OpenAI: TravelRoute(route = "openai")
    object TravelDetails: TravelRoute(route = "detail/{travelId}"){
        fun createRoute(travelId: Int) = "detail/$travelId"
    }

}