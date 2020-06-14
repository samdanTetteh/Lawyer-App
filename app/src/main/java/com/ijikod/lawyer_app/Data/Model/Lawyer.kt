package com.ijikod.lawyer_app.Data.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Lawyers")
data class Lawyer (
    @PrimaryKey()
    val id: Long?,
    @field:Json(name = "first_name")
    val firstName: String?,
    @field:Json(name = "last_name")
    val lastName: String?,
    val speciality: String,
    val rating: String?,
    val rate: String?,
    @field:Json(name = "review_count")
    val reviewCount: String?,
    val featured: String?,
    val ranking: String?,
    val fav: String?,
    val verified : String?,
    val memberSince: String?,
    val avgResponseTime: String?,
    val description: String?,
    val moreInfo: String?,
    val avatar: String?
) : Parcelable