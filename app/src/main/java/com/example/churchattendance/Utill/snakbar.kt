package com.example.churchattendance.Utill

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun snakbar(
    state: Boolean = true,
    msg:String,
    scaffoldState: ScaffoldState,
) {
    // If the UI state contains an error, show snackbar
    if (state) {

        // `LaunchedEffect` will cancel and re-launch if
        // `scaffoldState.snackbarHostState` changes
        LaunchedEffect(scaffoldState.snackbarHostState) {
            // Show snackbar using a coroutine, when the coroutine is cancelled the
            // snackbar will automatically dismiss. This coroutine will cancel whenever
            // `state.hasError` is false, and only start when `state.hasError` is true
            // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
            scaffoldState.snackbarHostState.showSnackbar(
                message = msg,
            )
        }
    }

}