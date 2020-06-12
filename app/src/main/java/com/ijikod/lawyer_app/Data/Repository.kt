package com.ijikod.lawyer_app.Data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.R
import com.ijikod.lawyer_app.Utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(val app : Application) {


    val lawyersData = MutableLiveData<List<Lawyer>>()
    val dao = ModelDatabase.getDatabase(app).lawyerDao()





    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.getLawyers()

            if (data.isEmpty()){
                getLawyersFromFileAndSave()
            }else{
                lawyersData.postValue(data)
            }
        }

    }





    @WorkerThread
    suspend fun getLawyersFromFileAndSave(){
        val lawyers = readDataFromCache()
        dao.deleteAll()
        dao.insertLawyers(lawyers)

        lawyersData.postValue(dao.getLawyers())
    }





    private fun readDataFromCache(): List<Lawyer> {
        val json = FileHelper.getTextFromAssets(app, app.getString(R.string.lawyer_file_name))
        if (json == null) {
            return emptyList()
        }
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Lawyer::class.java)
        val adapter: JsonAdapter<List<Lawyer>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

}