package rs.ac.metropolitan.cs330_pz.screens.dialogs

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
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
