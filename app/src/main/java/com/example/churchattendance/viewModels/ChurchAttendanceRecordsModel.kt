package com.example.churchattendance.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchattendance.Network.Resource
import com.example.churchattendance.Repo.Repostuff
import com.example.kabetegaterecoder.Models.MyRecordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChurchAttendanceRecordsModel @Inject constructor(private val repostuff: Repostuff) : ViewModel() {
    private val _urlzResponses = MutableStateFlow<Resource<List<MyRecordResponse>>?>(null)
    val urlzResponses: StateFlow<Resource<List<MyRecordResponse>>?> = _urlzResponses
    val foldername = MutableStateFlow("")

    fun viewrecords()=viewModelScope.launch {
        _urlzResponses.value = Resource.Loading
        _urlzResponses.value=repostuff.viewrecords()
    }
}