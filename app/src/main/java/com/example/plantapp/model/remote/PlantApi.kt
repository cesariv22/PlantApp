package com.example.plantapp.model.remote

import com.example.plantapp.model.remote.fromInternet.PlantDetail
import com.example.plantapp.model.remote.fromInternet.Plants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantApi {
    @GET("plantas")
    suspend fun fetchPlantList(): Response<List<Plants>>

    @GET("plantas/{id}")
    suspend fun fetchPlantDetail(@Path("id") id:String) : Response<PlantDetail>
}