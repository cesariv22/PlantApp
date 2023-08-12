package com.example.plantapp.model

import android.util.Log
import com.example.plantapp.model.local.PlantDao
import com.example.plantapp.model.local.entities.PlantDetailEntity
import com.example.plantapp.model.remote.RetrofitClient


class PlantRepository(private val plantDao: PlantDao) {

    //Retrofit Client
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val plantsListLiveData = plantDao.getAllPlants()


    suspend fun fetchPlant() {
        val service = kotlin.runCatching { networkService.fetchPlantList() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("Plants", it.toString())
                    plantDao.insertAllPlants(fromInternetPlantsEntity(it))
                }
                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }

    suspend fun fetchPlantDetail(id: String): PlantDetailEntity? {
        val service = kotlin.runCatching { networkService.fetchPlantDetail(id) }
        return service.getOrNull()?.body()?.let { plantDetail ->
            // Guardo los datos que vienen del mapper y luego se los paso a Dao directo
            val plantDetailEntity = fromInternetPlantDetailEntity(plantDetail)
            //Inserto los detalles de las plantas del Repositorio
            plantDao.insertPlantDetail(plantDetailEntity)
            plantDetailEntity
        }
    }
}