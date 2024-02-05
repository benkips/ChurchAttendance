package com.example.churchattendance.Navigation


sealed class Screens(val route : String) {
    object Add_records : Screens("Add_records")
    object View_records : Screens("View_records")
}