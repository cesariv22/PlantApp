package com.example.plantapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantapp.model.local.entities.PlantDetailEntity
import com.example.plantapp.model.local.entities.PlantEntity


@Dao
interface PlantDao {

    //Insertar lista de plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlants(listPlants: List<PlantEntity>)

    //Seleccionar listado de plantas
    @Query("SELECT * FROM plant_list_table ORDER BY id ASC")
    fun getAllPlants(): LiveData<List<PlantEntity>>

    //Inserta una planta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlantDetail(plant: PlantDetailEntity)

    @Query("SELECT * FROM plant_detail_table WHERE id=:id")
    fun getPlantDetailById(id: String): LiveData<PlantDetailEntity>

}