package com.example.churchattendance.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchattendance.Network.Resource
import com.example.churchattendance.Repo.Repostuff
import com.example.kabetegaterecoder.Models.MyRecordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChurchAttendanceRecordsModel @Inject constructor(private val repostuff: Repostuff) : ViewModel() {
    private val _urlzResponses: MutableLiveData<Resource<List<MyRecordResponse>>> = MutableLiveData()
    val urlzResponses: LiveData<Resource<List<MyRecordResponse>>>
        get() = _urlzResponses

    fun viewrecords()=viewModelScope.launch {
        _urlzResponses.value = Resource.Loading
        _urlzResponses.value=repostuff.viewrecords()
    }
}