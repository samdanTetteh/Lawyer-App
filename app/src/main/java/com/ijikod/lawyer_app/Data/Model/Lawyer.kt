package com.ijikod.lawyer_app.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Lawyers")
data class Lawyer (
    @PrimaryKey()
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val speciality: String,
    val rating: String?,
    val rate: String?,
    val reviewCount: String?,
    val featured: String?,
    val ranking: String?,
    val fav: String?,
    val memberSince: String?,
    val avgResponseTime: String?,
    val description: String?,
    val moreInfo: String?,
    val avatar: String?
)