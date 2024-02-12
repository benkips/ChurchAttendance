package com.example.churchattendance.Repo

import com.example.churchattendance.Network.ApiInterface
import javax.inject.Inject

class Repostuff @Inject constructor (private val apiInterface: ApiInterface):Baserepository(){

    //add records
    suspend fun addRecords(
        x:String,
        y:String,
        z:String,
        q:String,
        r:String,
    ) = safeApiCall {
        apiInterface.addRecords(x,y,z,q,r)
    }

    suspend fun viewrecords() = safeApiCall {
        apiInterface.viewrecords()
    }
    suspend fun getpdfdata( x:String,) = safeApiCall {
        apiInterface.getmydata( x)
    }
}