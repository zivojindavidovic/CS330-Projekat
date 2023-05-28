package rs.ac.metropolitan.cs330_pz

import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppViewModel(): ViewModel() {

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

}