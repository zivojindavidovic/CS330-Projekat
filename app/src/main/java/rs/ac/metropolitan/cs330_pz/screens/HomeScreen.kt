package rs.ac.metropolitan.cs330_pz.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import rs.ac.metropolitan.cs330_pz.AppViewModel

@Composable
fun HomeScreen(vm: AppViewModel){
    Column {
        Text(text = "Hello Welcome Screen")
    }
    Column {
        Button(onClick = {
            vm.navigateToMapScreen()
        }) {
            Text(text = "Map Screen")
        }
    }

}