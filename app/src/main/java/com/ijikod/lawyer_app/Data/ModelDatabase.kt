package com.ijikod.lawyer_app.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ijikod.lawyer_app.Data.Model.Lawyer


@Database(entities = [Lawyer::class], version = 1, exportSchema = false)
abstract class ModelDatabase : RoomDatabase(){


    abstract fun lawyerDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: ModelDatabase? = null

        fun getDatabase(context: Context): ModelDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ModelDatabase::class.java,
                        "checkfer.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}