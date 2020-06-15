package com.ijikod.lawyer_app

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.Data.ModelDatabase
import com.ijikod.lawyer_app.Utilities.TestFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTests {

    private lateinit var lawyerDatabase: ModelDatabase


    @Before
    fun initDb() {
        lawyerDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ModelDatabase::class.java).build()
    }


    @Test
    fun insertBeersSavesData() {
        val cachedLawyers = Gson().fromJson(TestFactory.lawyers, Array<Lawyer>::class.java).asList()
        lawyerDatabase.lawyerDao().insertLawyersTest(cachedLawyers)

        val lawyers = lawyerDatabase.lawyerDao().getLawyers()
        assert(lawyers.isNotEmpty())
    }
}