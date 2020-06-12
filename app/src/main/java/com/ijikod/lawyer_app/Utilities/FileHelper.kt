package com.ijikod.lawyer_app.Utilities

import android.app.Application
import android.content.Context
import java.io.File

class FileHelper {

    companion object{

        fun getTextFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }


        fun readTextFile(app: Application): String? {
            val file = File(app.getExternalFilesDir("lawyers"), "lawyer.json")
            return if (file.exists()) {
                file.readText()
            } else null
        }
    }


}