package rs.ac.metropolitan.cs330_pz.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rs.ac.metropolitan.cs330_pz.AppViewModel
import rs.ac.metropolitan.cs330_pz.screens.dialogs.RequestInternetPermissionsDialog

@Composable
fun HomeScreen(vm: AppViewModel){

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted->
        vm.grantedInternetPermission.value = isGranted
    }

    if(!vm.grantedInternetPermission.value){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                RequestInternetPermissionsDialog(launcher)
        }
    }else{
        Column {
            DestinationsList(vm)
        }
    }

}

@Composable
fun DestinationsList(vm: AppViewModel){
    Text(text = "Lista Destinacija")
    for(i in 1..3){
        SingleDestination()
    }
}

@Composable
fun SingleDestination(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = "Beograd - Novi Sad")
        }
        Column {
            Text(text = "10 Km")
        }
        Spacer(
            modifier = Modifier.
                    weight(1f)
        )
        Icon(
            Icons.Default.ArrowForward,
            contentDescription = "View Destination",
            modifier = Modifier.clickable {

            }
        )
    }
}

@Preview
@Composable
fun SingleDestinationPreview(){
    SingleDestination()
}
