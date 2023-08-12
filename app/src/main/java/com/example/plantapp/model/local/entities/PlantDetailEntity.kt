package com.example.plantapp.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_detail_table")
class PlantDetailEntity(
    @PrimaryKey
    val id: String,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String
)