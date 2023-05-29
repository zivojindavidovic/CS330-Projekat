package rs.ac.metropolitan.cs330_pz.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_pz.AppViewModel
import rs.ac.metropolitan.cs330_pz.model.Travel
import rs.ac.metropolitan.cs330_pz.TravelState

@Composable
fun TravelScreen(vm: AppViewModel, travelId: Int, state: TravelState){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Row() {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                item {
                    ShowTravel(travel = vm.getTravel(travelId, state), seeStops = {vm.switchSeeStops()}, goBack = {vm.goBack()}, isExpanded = vm.seeStops.value)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            AnimatedVisibility(vm.seeStops.value) {
                vm.getStopsList(travelId, state = state)
                    ?.let { TravelStops(travel = vm.getTravel(travelId, state), stopsList = it) }
            }
        }
    }
}

@Composable
fun ShowTravel(travel: Travel?, seeStops: () -> Unit, goBack: () -> Unit, isExpanded: Boolean){
    var expended by remember {
        mutableStateOf(false)
    }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        IconButton(
            modifier = Modifier
            .background(Color.Transparent),
            onClick = {
                goBack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        travel?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Column {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Start")
                    }
                    Column {
                        Text(text = "Travel from: ${travel.travelFrom}")
                    }
                }
                Row {
                    Column {
                        Icon(imageVector = Icons.Default.Place, contentDescription = "Destination")
                    }
                    Column {
                        Text(text = "Travel to: ${travel.travelTo}")
                    }
                }
                Row {
                    Column {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date")
                    }
                    Column {
                        Text(text = "Travel date: ${travel.travelDate}")
                    }
                }
                Row {
                    Column {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Distance")
                    }
                    Column {
                        Text(text = "Travel date: ${travel.travelDistance}")
                    }
                }
                Button(
                    onClick = {
                        seeStops()
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    if(isExpanded){
                        Text(text = "Hide Stops")
                    }else{
                        Text(text = "Show Stops")
                    }
                }
            }
        }
    }
}

@Composable
fun TravelStops(travel: Travel?, stopsList: List<String>){
    LazyColumn(){
        travel?.let {
            items(stopsList){stop ->

                TravelStop(stop = stop)
            }
        }
    }
}
@Composable
fun TravelStop(stop: String){
    Text(text = "${stop}")
}