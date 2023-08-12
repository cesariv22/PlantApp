package com.example.plantapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp.model.PlantRepository
import com.example.plantapp.model.local.dataBase.PlantDataBase
import com.example.plantapp.model.local.entities.PlantDetailEntity
import com.example.plantapp.model.local.entities.PlantEntity
import kotlinx.coroutines.launch

class PlantViewModel (application: Application) : AndroidViewModel(application) {

    // conexión repository
    private val repository: PlantRepository

    // entidad PlantDetail
    private val plantDetailLiveData = MutableLiveData<PlantDetailEntity>()

 /*   // para seleccionar
    private var idSelected: String = "-1"*/


    init {
        // Tiene la instancia de la base de dato, el dao y lo entregamos el repository
        val bd = PlantDataBase.getDataBase(application)
        val plantDao = bd.getPlantDao()

        repository = PlantRepository(plantDao)

        // llamo al método del repository
        viewModelScope.launch {
            repository.fetchPlant()
        }
    }

    // Listado de los elementos
    fun getPlantList(): LiveData<List<PlantEntity>> = repository.plantsListLiveData

    // Para obtener una planta por id desde lo que se selecciono
    fun getPlantDetail(): LiveData<PlantDetailEntity> = plantDetailLiveData

    // Desde el primer fragmento le paso la seleccion al segundo fragmento
    fun getPlantDetailByIdFromInternet(id: String) = viewModelScope.launch {
        val plantDetail = repository.fetchPlantDetail(id)
        plantDetail?.let {
            plantDetailLiveData.postValue(it)
        }
    }
}
