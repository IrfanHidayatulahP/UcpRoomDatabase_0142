package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.view.DashBoardView
import com.example.ucp2.ui.view.Dosen.DestinasiInsert
import com.example.ucp2.ui.view.Dosen.HomeDsnView
import com.example.ucp2.ui.view.Dosen.InsertDsnView
import com.example.ucp2.ui.view.MatKul.DestinasiInsertMK
import com.example.ucp2.ui.view.MatKul.HomeMKView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(DestinasiHome.route) {
            DashBoardView(
                navController = navController
            )
        }

        composable(
            route = DestinasiHomeDsn.route
        ) {
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsert.route
        ) {
            InsertDsnView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeMK.route
        ) {
            HomeMKView(
                onAddMK = {
                    navController.navigate(DestinasiInsertMK.route)
                },
                modifier = modifier
            )
        }
    }
}