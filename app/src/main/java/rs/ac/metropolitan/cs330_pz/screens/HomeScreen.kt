package rs.ac.metropolitan.cs330_pz.screens

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_pz.AppViewModel

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
        DestinationsList(vm)
    }

}

@Composable
fun DestinationsList(vm: AppViewModel){
    Text(text = "Lista Destinacija")
}

@Composable
fun RequestInternetPermissionsDialog(
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Internet Permissions Required")},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){

            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(
                    onClick = {
                        launcher.launch(Manifest.permission.INTERNET)
                    }
                ) {
                    Text(text = "Allow")
                }
            }
        }
    )
}