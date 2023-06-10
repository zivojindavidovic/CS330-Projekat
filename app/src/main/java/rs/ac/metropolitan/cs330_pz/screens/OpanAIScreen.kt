package rs.ac.metropolitan.cs330_pz.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_pz.AppViewModel

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenAIScreen(context: Context, vm: AppViewModel){
    var question by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ) {
        Row() {
            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                label = { Text("Label") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    vm.sendRequest(question = question)
                    question = ""
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondaryContainer),
                enabled = !vm.isLoading
            ) {
                Text(text = "Search")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = vm.answer)
        }
    }
}