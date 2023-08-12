package com.example.plantapp.model

import com.example.plantapp.model.local.entities.PlantDetailEntity
import com.example.plantapp.model.local.entities.PlantEntity
import com.example.plantapp.model.remote.fromInternet.PlantDetail
import com.example.plantapp.model.remote.fromInternet.Plants


fun fromInternetPlantsEntity( plantsList: List<Plants>) :List<PlantEntity>{
    return plantsList.map {
        PlantEntity(
            id=it.id,
            nombre= it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion
        )
    }
}

fun fromInternetPlantDetailEntity (plant: PlantDetail) : PlantDetailEntity {
    return PlantDetailEntity(
        id=plant.id,
        nombre= plant.nombre,
        tipo = plant.tipo,
        imagen = plant.imagen,
        descripcion = plant.descripcion,
    )
}
