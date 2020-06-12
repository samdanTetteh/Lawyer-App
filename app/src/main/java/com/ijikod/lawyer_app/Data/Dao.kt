package com.ijikod.lawyer_app.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ijikod.lawyer_app.Data.Model.Lawyer


@Dao
interface Dao {
    @Query("SELECT * from lawyers order by firstName ASC")
    fun getLawyers(): List<Lawyer>

    @Insert
    suspend fun insertLawyer(lawyer: Lawyer)

    @Insert
    suspend fun insertLawyers(monsters: List<Lawyer>)

    @Query("DELETE from lawyers")
    suspend fun deleteAll()
}