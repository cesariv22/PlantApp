package com.example.plantapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plantapp.model.local.PlantDao
import com.example.plantapp.model.local.dataBase.PlantDataBase
import com.example.plantapp.model.local.entities.PlantDetailEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlantInstrumentedTest {

    private lateinit var plantDao: PlantDao
    private lateinit var db: PlantDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, PlantDataBase::class.java).build()
        plantDao = db.getPlantDao()
    }

    @After
    fun shutDown() {
        db.close()
    }

    @Test
    fun insertDetailPlant() = runBlocking {
        val plantDetail = PlantDetailEntity(
            "2",
            "plant 2",
            "type 2",
            "url",
            "description"
        )
        plantDao.insertPlantDetail(plantDetail)

        MatcherAssert.assertThat(plantDetail.id, CoreMatchers.equalTo("2"))
        MatcherAssert.assertThat(plantDetail.nombre, CoreMatchers.equalTo("plant 2"))
    }
}

