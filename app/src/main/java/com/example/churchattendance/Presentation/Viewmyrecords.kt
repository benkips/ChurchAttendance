package com.example.churchattendance.Presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.churchattendance.Models.Data
import com.example.churchattendance.Network.Resource
import com.example.churchattendance.R
import com.example.churchattendance.Utill.FullScreenProgressbar
import com.example.churchattendance.Utill.RetrySection
import com.example.churchattendance.viewModels.ChurchAttendanceRecordsModel

@Composable
fun Viewmyrecords(navController: NavController,viewModel: ChurchAttendanceRecordsModel)  {

    val days = viewModel.daysitems.collectAsState()
    LaunchedEffect(key1 = days.value) {
        viewModel.viewrecords()
    }

    val dayslist = viewModel.urlzResponses?.collectAsState()
    Log.d("dayslist", "dayslist: " + dayslist)
    dayslist?.value.let {
        when (it) {
            is Resource.Failure -> {
                RetrySection(
                    error = it.errorBody!!,
                    onRetry = { viewModel.viewrecords() })
            }

            Resource.Loading -> {
                FullScreenProgressbar()
            }

            is Resource.Success -> {

                DaysList(it.value.data, navController, viewModel)
            }

            else -> {
                /*  Toast.makeText(context, "error loading".toString(), Toast.LENGTH_SHORT).show()*/
            }
        }
    }

      //folderdetails
    //   val foldertype = viewModel.foldername.collectAsState()


}


@Composable
fun DaysList(
    days: List<Data>,
    navController: NavController,
    viewModel: ChurchAttendanceRecordsModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 20.dp)

        ) {
            Text(
                text = "Recordings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(1.dp)
            )
            LazyColumn(
                Modifier.padding(start = 10.dp, end = 10.dp)
            ) {
                items(days) {
                    TeachingCardItem(
                        navController = navController,
                        item = it,
                        R.drawable.baseline_edit_calendar_24,
                        viewModel
                    )
                }
            }

        }
    }
}

@Composable
fun TeachingCardItem(
    navController: NavController,
    item: Data,
    imagevector: Int,
    viewModel: ChurchAttendanceRecordsModel
) {
    val contextForToast = LocalContext.current.applicationContext

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(top = 8.dp, end = 5.dp)
            .clickable {
                /*viewModel.setUpdating(item)
                viewModel.getpdf(item.date)*/
                //navController.navigate(Constants.Screens.CONTENT_SCREEN)
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = imagevector),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(color = Color(0xFFD81B60))
            )


            Column(Modifier.padding(start = 5.dp)) {
                Text(
                    text = item.date,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row {

                    Text(
                        text = item.records.toString() + " Records",
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                    )
                    Spacer(Modifier.weight(0.8f))
                    Column(Modifier.padding(start = 5.dp)) {
                        Button(
                            onClick = {
                                //viewModel.setUpdating(item)
                                Toast.makeText(contextForToast, "clicked", Toast.LENGTH_SHORT).show()
                                //viewModel.getpdf(item.date)
                            },
                            shape = RoundedCornerShape(topEnd = 0.dp, topStart = 10.dp, bottomEnd = 0.dp, bottomStart = 10.dp),
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(0.5f),

                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD81B60))
                        ) {
                            androidx.compose.material3.Text(
                                text = "Download",
                                color = Color.White

                            )

                        }

                    }
                }
            }


        }
    }

}