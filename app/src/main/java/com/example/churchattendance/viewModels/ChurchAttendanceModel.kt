package com.example.churchattendance.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchattendance.Network.Resource
import com.example.churchattendance.Repo.Repostuff
import com.example.kabetegaterecoder.Models.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChurchAttendanceModel @Inject constructor(private val repostuff: Repostuff) : ViewModel() {

    private val _urlzResponse=MutableStateFlow<Resource<MyResponse>?>(null)

    val urlzResponse: StateFlow<Resource<MyResponse>>
        get() = _urlzResponse


    fun addrecords(
        x: String,
        y: String,
        z: String,
        q: String,
        r: String,
    ) = viewModelScope.launch {
        _urlzResponse.value = Resource.Loading
        _urlzResponse.value = repostuff.addRecords(
            x,
            y,
            z,
            q,
            r,
        )
    }



}