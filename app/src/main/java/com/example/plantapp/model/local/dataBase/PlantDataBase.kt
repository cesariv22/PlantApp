package com.example.plantapp.model.local.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantapp.model.local.PlantDao
import com.example.plantapp.model.local.entities.PlantDetailEntity
import com.example.plantapp.model.local.entities.PlantEntity


@Database(
    entities = [PlantEntity::class, PlantDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PlantDataBase : RoomDatabase() {
    //Referencia del Dao
    abstract fun getPlantDao(): PlantDao

    companion object {

        @Volatile
        private var INSTANCE: PlantDataBase? = null

        fun getDataBase(context: Context) : PlantDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDataBase::class.java, "plant_db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}