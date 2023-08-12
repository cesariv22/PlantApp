package com.example.plantapp

import com.example.plantapp.model.local.entities.PlantEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class PlantUnitTest {

    private lateinit var plantEntity: PlantEntity

    @Before
    fun setup() {
        plantEntity = PlantEntity(
            id = "1",
            nombre = "nombre prueba test",
            tipo = "tipo prueba test",
            imagen = "url test",
            descripcion = "descripcion test"
        )
    }

    @Test
    fun testPlant(){
        //Verificar valores asignados
        assert(plantEntity.id=="1")
        assert(plantEntity.nombre=="nombre prueba test")
        assert(plantEntity.tipo=="tipo prueba test")
        assert(plantEntity.imagen=="url test")
        assert(plantEntity.descripcion=="descripcion test")
    }
}