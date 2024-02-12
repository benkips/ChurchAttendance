package com.example.churchattendance.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.churchattendance.Models.Data
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
    private val _urlzResponses = MutableStateFlow<Resource<MyRecordResponse>?>(null)
    val urlzResponses: StateFlow<Resource<MyRecordResponse>?> = _urlzResponses
    val daysitems = MutableStateFlow("")

    fun viewrecords()=viewModelScope.launch {
        _urlzResponses.value = Resource.Loading
        _urlzResponses.value=repostuff.viewrecords()
    }

    fun setUpdating(daysItem: Data?) {
        if (daysItem != null) {
            daysitems.value=daysItem.date
        } else {
            daysitems.value= ""
        }
    }
    fun getpdf(x: String)=viewModelScope.launch {
         repostuff.getpdfdata(x)
    }

}