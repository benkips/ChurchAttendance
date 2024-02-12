package com.example.churchattendance.Network

import com.example.kabetegaterecoder.Models.MyRecordResponse
import com.example.kabetegaterecoder.Models.MyResponse
import retrofit2.http.*


interface ApiInterface {
    companion object{
        const val BASE_URL="https://churchattendance.howtoinkenya.co.ke/mobiadmin/"
    }


    @POST("addrecord")
    @FormUrlEncoded
    suspend  fun addRecords(
        @Field("fullnames") fullnames: String?,
        @Field("phone") phone: String?,
        @Field("age") age: String?,
        @Field("residence") residence: String?,
        @Field("fellowship") fellowship: String?,
    ): MyResponse


    //Getting records
    @GET("viewrecords")
    suspend  fun viewrecords(): MyRecordResponse
    //Getting records
    @GET("gettabledata")
    suspend  fun viewrecords(): MyRecordResponse
}