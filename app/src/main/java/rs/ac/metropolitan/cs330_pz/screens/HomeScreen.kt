package rs.ac.metropolitan.cs330_pz.screens

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_pz.AppViewModel
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
            DestinationsList(state)
        }
    }
}

@Composable
fun DestinationsList(state: TravelState){
    LazyColumn(){
        items(state.travels){travel ->
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

                                }
                        )
                    }
                }
            }
        }
    }
}
