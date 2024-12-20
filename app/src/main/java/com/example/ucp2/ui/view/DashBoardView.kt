package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ucp2.ui.navigation.AlamatNavigasi

object DestinasiHome : AlamatNavigasi {
    override val route: String = "DashBoard"
}

@Composable
fun DashBoardView(
    navController: NavController,
    onClick: () -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("HomeDosen") }) {
            Text(text = "Go to Home Dosen")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("HomeMK") }) {
            Text(text = "Go to Home Mata Kuliah")
        }
    }
}