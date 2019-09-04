package com.example.medicalcenterapp.utilities

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users")
    fun createUser(@Query("username") user:String): Call<String>
}