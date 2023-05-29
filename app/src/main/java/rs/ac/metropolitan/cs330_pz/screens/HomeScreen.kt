package rs.ac.metropolitan.cs330_pz.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_pz.AppViewModel
import rs.ac.metropolitan.cs330_pz.model.Travel
import rs.ac.metropolitan.cs330_pz.TravelState
import rs.ac.metropolitan.cs330_pz.screens.dialogs.RequestInternetPermissionsDialog


@Composable
fun HomeScreen(vm: AppViewModel, state: TravelState){
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted->
        vm.grantedInternetPermission.value = isGranted
    }
    if(!vm.grantedInternetPermission.value){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            RequestInternetPermissionsDialog(launcher)
        }
    }else{
        Column {
            DestinationsList(state, vm)
        }
    }
}

@Composable
fun DestinationsList(state: TravelState, vm: AppViewModel){
    LazyColumn(){
        items(state.travels){travel ->
            SingleTravel(travel = travel){
                vm.navigateToTravelDetails(it)
            }
        }
    }
}

@Composable
fun SingleTravel(travel: Travel, onTravelSelected: (Int) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
            Column {
                Row {
                    Text(text = "${travel.travelFrom} - ${travel.travelTo}")
                }
                Row {
                    Text(text = "${travel.travelDate}")
                }
            }
            Spacer(
                modifier = Modifier.
                weight(1f)
            )
            Column {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "View Destination",
                    modifier = Modifier
                        .clickable {
                            onTravelSelected(travel.id)
                        }
                )
            }
        }
    }
}
