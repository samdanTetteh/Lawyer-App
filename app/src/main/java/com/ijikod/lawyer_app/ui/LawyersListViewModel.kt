package com.ijikod.lawyer_app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.Data.Repository

class LawyersListViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository(application)

    val lawyerData = repo.lawyersAllData
    val numberOfFeaturedLawyers = repo.lawyersFeaturedCount
    val numberOfFavLawyers = repo.lawyersFavCount
    val featuredLawyers = repo.lawyersFeaturedData
    val favLawyerData = repo.lawyersFavData

    val selectedLawyer = MutableLiveData<Lawyer>()


}