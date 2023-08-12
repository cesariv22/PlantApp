package com.example.plantapp.viewUI

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.AdapterPlant
import com.example.plantapp.R
import com.example.plantapp.databinding.FragmentFirstBinding
import com.example.plantapp.viewModel.PlantViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val mViewModel : PlantViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instanciar el adapter
        val adapter = AdapterPlant()
        binding.rvPlant.adapter= adapter
        //Pasar recycler con grid de 2 columnas
        binding.rvPlant.layoutManager= GridLayoutManager(context, 2)

        //Dar margen entre cards en el recycler
        val spacing = resources.getDimensionPixelSize(R.dimen.fab_margin)
        binding.rvPlant.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.apply {
                    left = spacing / 2
                    right = spacing / 2
                    top = spacing
                    bottom = spacing
                }
            }
        })

        //Obtener la lista de plantas y se actualiza.
        mViewModel.getPlantList().observe(viewLifecycleOwner) {
            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }
        }

        //Método para seleccionar una planta, navegar y pasar los datos al segundo fragmento
        adapter.selectedPlant().observe(viewLifecycleOwner) {
            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion", it.id)
            }

            val bundle = Bundle().apply {
                putString("plantId", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}