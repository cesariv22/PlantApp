package com.example.plantapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.databinding.ItemPlantBinding
import com.example.plantapp.model.local.entities.PlantEntity

class AdapterPlant : RecyclerView.Adapter<AdapterPlant.ViewHolderPlant>() {

    private var listPlants = listOf<PlantEntity>()
    private val selectedPlant = MutableLiveData<PlantEntity>()

    fun update(list: List<PlantEntity>) {
        listPlants = list
        notifyDataSetChanged()
    }

    // Función para seleccionar
    fun selectedPlant(): LiveData<PlantEntity> = selectedPlant

    inner class ViewHolderPlant(private val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(plant: PlantEntity) {
            Glide.with(binding.plantImageView).load(plant.imagen).into(binding.plantImageView)
            binding.plantNameTextView.text = plant.nombre
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // Referencia a la selección
            selectedPlant.value = listPlants[adapterPosition]
            Log.d("ONCLICK", adapterPosition.toString())
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterPlant.ViewHolderPlant {
        return ViewHolderPlant(ItemPlantBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterPlant.ViewHolderPlant, position: Int) {
        val plant= listPlants[position]
        holder.bind(plant)
    }

    override fun getItemCount()= listPlants.size
}