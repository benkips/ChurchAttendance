package com.example.churchattendance.Navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.churchattendance.Presentation.Addmyrecords
import com.example.churchattendance.Presentation.Viewmyrecords
import com.example.churchattendance.viewModels.ChurchAttendanceModel
import com.example.churchattendance.viewModels.ChurchAttendanceRecordsModel

@Composable
fun SetupNavGraph(navController: NavHostController, scaffoldState: ScaffoldState,) {

    val ChurchAttendanceModel = hiltViewModel<ChurchAttendanceModel>()
    val ChurchAttendanceRecordsModel = hiltViewModel<ChurchAttendanceRecordsModel>()

    NavHost(
        navController = navController,
        startDestination = Screens.Add_records.route){
        composable(Screens.Add_records.route) {
            Addmyrecords(
                navController,ChurchAttendanceModel,scaffoldState
            )
        }
        composable(Screens.View_records.route) {
            Viewmyrecords(
                navController,ChurchAttendanceRecordsModel
            )
    }


}
}