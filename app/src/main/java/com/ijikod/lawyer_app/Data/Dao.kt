package com.ijikod.lawyer_app.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ijikod.lawyer_app.Data.Model.Lawyer


@Dao
interface Dao {
    @Query("SELECT * from lawyers order by firstName ASC")
    fun getLawyers(): List<Lawyer>

    @Query("SELECT * from lawyers where featured = 1 order by firstName ASC")
    fun getFeaturedLawyers(): List<Lawyer>

    @Query("SELECT * from lawyers where fav = 1 order by firstName ASC")
    fun getFavLawyers(): List<Lawyer>

    @Query("SELECT COUNT(featured) from lawyers where featured = 1 order by firstName ASC")
    fun getFeaturedLawyerCount(): Int

    @Query("SELECT COUNT(fav) from lawyers where fav = 1 order by firstName ASC")
    fun getFavLawyerCount(): Int

    @Insert
    suspend fun insertLawyer(lawyer: Lawyer)

    @Insert
    suspend fun insertLawyers(lawyers: List<Lawyer>)

    @Insert
    fun insertLawyersTest(lawyers: List<Lawyer>)

    @Query("DELETE from lawyers")
    suspend fun deleteAll()
}