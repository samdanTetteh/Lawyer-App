package com.ijikod.lawyer_app.Data

import android.app.Application
import android.util.Log
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


    val lawyersAllData = MutableLiveData<List<Lawyer>>()
    val lawyersFavData = MutableLiveData<List<Lawyer>>()
    val lawyersFeaturedData = MutableLiveData<List<Lawyer>>()
    val lawyersFeaturedCount = MutableLiveData<Int>()
    val lawyersFavCount = MutableLiveData<Int>()

    val dao = ModelDatabase.getDatabase(app).lawyerDao()





    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.getLawyers()

            if (data.isEmpty()){
                getLawyersFromFileAndSave()
            }else{
                getFeatureCount()
                getFavCount()
                getAllLawyerData()

                getFeaturedData()
                getAllLawyerData()
                getFavData()
            }
        }

    }


    fun getAllLawyerData(){
        CoroutineScope(Dispatchers.IO).launch {
            lawyersAllData.postValue(dao.getLawyers())
        }
    }


    fun getFeaturedData(){
        CoroutineScope(Dispatchers.IO).launch {
            lawyersFeaturedData.postValue(dao.getFeaturedLawyers())
        }
    }


    fun getFavData(){
        CoroutineScope(Dispatchers.IO).launch {
            lawyersFavData.postValue(dao.getFavLawyers())
        }
    }


    fun getFeatureCount(){
        CoroutineScope(Dispatchers.IO).launch {
            lawyersFeaturedCount.postValue(dao.getFeaturedLawyerCount())
        }
    }

    fun getFavCount(){
        CoroutineScope(Dispatchers.IO).launch {
            lawyersFavCount.postValue(dao.getFavLawyerCount())
        }
    }







    @WorkerThread
    suspend fun getLawyersFromFileAndSave(){

        val lawyers = readDataFromCache()
        dao.deleteAll()
        dao.insertLawyers(lawyers)

        getFeatureCount()
        getFavCount()
        getAllLawyerData()

        getFeaturedData()
        getAllLawyerData()
        getFavData()

        Log.d("getLawyersFromFile", " getting feature count")
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