package rs.ac.metropolitan.cs330_pz.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_pz.AppViewModel
import rs.ac.metropolitan.cs330_pz.TravelEvent
import rs.ac.metropolitan.cs330_pz.TravelState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelScreen(vm: AppViewModel, state: TravelState, onEvent: (TravelEvent) -> Unit){
    val context = LocalContext.current
    var myDate = remember{
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(
        context,
        {
                _: DatePicker, Year: Int, Month: Int, DayOfMonth: Int ->
                myDate.value = "$DayOfMonth/${Month+1}/$Year"
        }, vm.travelYear, vm.travelMonth, vm.travelDay
    )

    Text(text = "Hello Add Screen")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row {
            OutlinedTextField(
                value = state.travelFrom,
                onValueChange = {
                    onEvent(TravelEvent.SetTravelFrom(it))
                },
                label = { Text("Start Destination") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row {
            OutlinedTextField(
                value = state.travelTo,
                onValueChange = {
                    onEvent(TravelEvent.SetTravelTo(it))
                },
                label = { Text("Last Destination") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row {
            OutlinedTextField(
                value = state.stops,
                onValueChange = {
                    onEvent(TravelEvent.SetStops(it))
                },
                label = { Text("Enter your stops here with , sign") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column{
                Text(text = "Selected date: ${myDate.value}")
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = {
                        datePickerDialog.show()
                        onEvent(TravelEvent.SetTravelDate(myDate.value))
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
                ) {
                    Text(text = "Pick date to travel")
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    onEvent(TravelEvent.SetDistance("400KM"))
                    onEvent(TravelEvent.SetTravelDate(myDate.value))
                    onEvent(TravelEvent.SaveTravel)
                    myDate.value = "";
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(text = "Reserve travel")
            }
        }
    }
}