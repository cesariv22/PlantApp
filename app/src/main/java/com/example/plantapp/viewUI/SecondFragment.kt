package com.example.plantapp.viewUI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.plantapp.R
import com.example.plantapp.databinding.FragmentSecondBinding
import com.example.plantapp.viewModel.PlantViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val mViewModel: PlantViewModel by activityViewModels()
    private var plantId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            plantId = bundle.getString("plantId")
            Log.d("seleccion1", plantId.toString())
        }

        //Obtener detalle de una planta por el ID.
        plantId?.let { id ->
            mViewModel.getPlantDetailByIdFromInternet(id)
        }

        //Obtener el detalle de una planta desde el viewModel.
        mViewModel.getPlantDetail().observe(viewLifecycleOwner) {
            Log.d("seleccion2", plantId.toString())

            //Cargamos los datos seleccionados desde el primer fragmento
            Glide.with(binding.imagePlantSelected).load(it.imagen).into(binding.imagePlantSelected)
            binding.tvPlant.text = it.nombre
            binding.tvTypePlant.text = it.tipo
            binding.tvDescription.text = it.descripcion
        }

        //Botón para enviar correo.
        binding.btnSendEmail.setOnClickListener {
            val productName = binding.tvPlant.text.toString()

            //Intent para enviar
            val intent = Intent(Intent.ACTION_SEND).apply {
                //Se parsea la dirección del correo.
                data = Uri.parse("mailto:luci@plantapp.cl")
                //Texto para el asunto
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por la planta $productName"
                )

                //Texto que se enviara en el cuerpo del correo.
                val message =
                    "Hola,\n\nVi la planta <b>$productName</b> y me gustaría que me contactaran a este correo o al siguiente número _________.\n\nQuedo atento."
                putExtra(Intent.EXTRA_TEXT, message)
            }
            //Con esto se elije que app de correo se desea utilizar. Intent, se ejecuta, y "Enviar correo" es el mensaje del cuadro de dialogo.
            startActivity(Intent.createChooser(intent, "Enviar correo"))
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}