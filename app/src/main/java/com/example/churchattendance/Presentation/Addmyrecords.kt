package com.example.churchattendance.Presentation

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.churchattendance.Network.Resource
import com.example.churchattendance.R
import com.example.churchattendance.Utill.DesignedProgressBar
import com.example.churchattendance.Utill.snakbar
import com.example.churchattendance.viewModels.ChurchAttendanceModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addmyrecords(navController: NavController, viewModel: ChurchAttendanceModel, scaffoldState: ScaffoldState,) {
    val savingchurchresponse = viewModel.urlzResponse.observeAsState()

    val contextForToast = LocalContext.current.applicationContext
   /* val scaffoldState = rememberScaffoldState()*/
    val alertmsg = remember { mutableStateOf("") }

    val fullnameState = remember { mutableStateOf("") }
    val phoneState = remember { mutableStateOf("") }
    val ageState = remember { mutableStateOf("") }
    val residenceState = remember { mutableStateOf("") }

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mCities = listOf("Uthiru", "Muthua", "Cooperation", "Ndumbuini","87","N/A","Vistor")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown




    Scaffold(scaffoldState = scaffoldState) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 8.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 20.dp)

        ) {



            Image(
                painter = painterResource(R.drawable.dove),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp),
                /*contentScale = ContentScale.FillWidth*/
            )
            Text(
                text = "KABETE ALTAR",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(1.dp)
            )
            Text(
                text = "Gate recorder",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(1.dp)
            )

            OutlinedTextField(
                value = fullnameState.value,
                onValueChange = { fullnameState.value = it },
                label = { Text("Enter full names") },maxLines = 1,
                singleLine = true,

                /*colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Blue)*/
            )

            OutlinedTextField(
                value = phoneState.value,
                onValueChange = { phoneState.value = it },
                label = { Text("Enter phone number") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                maxLines = 1,
                singleLine = true,
            )

            OutlinedTextField(
                value = ageState.value,
                onValueChange = { ageState.value = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                maxLines = 1,
                singleLine = true,
            )

            OutlinedTextField(
                value = residenceState.value,
                onValueChange = { residenceState.value = it },
                label = { Text("Residence") },
                maxLines = 1,
                singleLine = true,
            )

            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    /*.fillMaxWidth()*/
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Fellowship") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                },
                maxLines = 1,
                singleLine = true,
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() }
                    )
            ) {
                mCities.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }

            savingchurchresponse?.value.let {
                when (it) {
                    is Resource.Failure -> {
                       /* RetrySection(
                            error = it.errorBody!!,
                            onRetry = {})*/
                      Toast.makeText(contextForToast,it.errorBody!!, Toast.LENGTH_SHORT).show()
                    }

                    Resource.Loading -> {
                        DesignedProgressBar()
                    }

                    is Resource.Success -> {
                        alertmsg.value=it.value.suc
                        snakbar(msg =it.value.suc, scaffoldState = scaffoldState )
                        /*Toast.makeText(contextForToast,it.value.suc, Toast.LENGTH_SHORT).show()*/
                        alertmsg.value=""
                        Log.d("results", "results: " + it.value.suc)
                    }

                    else -> {
                        /*  Toast.makeText(context, "error loading".toString(), Toast.LENGTH_SHORT).show()*/
                    }
                }
            }

            Button(
                onClick = {

                    if (fullnameState.value.isEmpty() || phoneState.value.isEmpty()
                        || ageState.value.isEmpty() || residenceState.value.isEmpty()
                        || mSelectedText.isEmpty()
                        ) {
                          Toast.makeText(contextForToast,"Kindly fill all the details", Toast.LENGTH_SHORT).show()
                    }else{

                       viewModel.addrecords(fullnameState.value,phoneState.value,ageState.value,residenceState.value,mSelectedText)
                    }

                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD81B60))
            ) {
               Text(
                    text = "Save",
                    color = Color.White,
                    modifier = Modifier.padding(6.dp)

                )

            }

        }

    }
}
}


