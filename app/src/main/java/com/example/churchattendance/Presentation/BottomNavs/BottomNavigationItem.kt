package com.example.churchattendance.Presentation.BottomNavs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.churchattendance.Navigation.Screens

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Create,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Add records",
                icon =  Icons.Default.Create,
                route = Screens.Add_records.route
            ),
            BottomNavigationItem(
                label = "View records",
                icon = Icons.Filled.List,
                route = Screens.View_records.route
            )
        )

    }
}
