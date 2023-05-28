package rs.ac.metropolitan.cs330_pz

import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppViewModel(
    private val dao: TravelDao
): ViewModel() {

    lateinit var navController: NavHostController
    var grantedInternetPermission = mutableStateOf(false)

    var answer by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set

    fun navigateToMapScreen(){
        navController.navigate(TravelRoute.Map.route)
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api = retrofit.create(AiApi::class.java)

    fun sendRequest(question: String) {
        val request = AiRequest(
            prompt = question,
            max_tokens = 100,
            model = "text-davinci-003"
        )

        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val call = api.getCompletion(request)
            val response = call.execute()
            isLoading = false

            if (response.isSuccessful) {
                val choice = response.body()?.choices?.get(0)
                viewModelScope.launch(Dispatchers.Main) {
                    choice?.text?.let {
                        answer = it
                    }
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    answer = "Error: ${response.code()} - ${response.message()}"
                }
            }
        }
    }

    //addTravelScreen
    val calendar = Calendar.getInstance()

    var travelYear = calendar.get(Calendar.YEAR)
    var travelMonth = calendar.get(Calendar.MONTH)
    var travelDay = calendar.get(Calendar.DAY_OF_MONTH)

    //adding Travel to Database

    private val _state = MutableStateFlow(TravelState())
    private val _travels = dao.getAllTravels()
    val state = combine(_state, _travels){state, travels->
            state.copy(
                travels = travels
            )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TravelState())

    fun onEvent(event: TravelEvent){
        when(event){
            is TravelEvent.DeleteTravel -> {
                viewModelScope.launch {
                    dao.deleteTravel(event.travel)
                }
            }
            TravelEvent.SaveTravel -> {
                val travelFrom = _state.value.travelFrom
                val travelTo = _state.value.travelTo
                val travelStops = _state.value.stops
                val travelDate = _state.value.date
                val travelDistance = _state.value.distance
                if(
                    travelFrom.isBlank()
                    || travelTo.isBlank()
                    || travelStops.isBlank()
                    //|| travelDate.isBlank()
                    //|| travelDistance.isBlank()
                ){
                    return
                }
                val travel = Travel(
                    travelFrom = travelFrom,
                    travelTo = travelTo,
                    travelStops = travelStops,
                    travelDate = travelDate,
                    travelDistance = travelDistance
                )
                viewModelScope.launch {
                    dao.upsertTravel(travel)
                    _state.update { it.copy(
                        travelFrom = "",
                        travelTo = "",
                        stops = "",
                        date = "",
                        distance = ""
                    ) }
                }
            }
            is TravelEvent.SetDistance -> {
                _state.update { it.copy(
                    distance = event.distance
                ) }
            }
            is TravelEvent.SetStops -> {
                _state.update { it.copy(
                    stops = event.stops
                ) }
            }
            is TravelEvent.SetTravelDate -> {
                _state.update { it.copy(
                    date = event.date
                ) }
            }
            is TravelEvent.SetTravelFrom -> {
                _state.update { it.copy(
                    travelFrom = event.travelFrom
                ) }
            }
            is TravelEvent.SetTravelTo -> {
                _state.update { it.copy(
                    travelTo = event.travelTo
                ) }
            }
        }
    }
}