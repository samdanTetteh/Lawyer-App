package com.ijikod.lawyer_app

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.Utilities.LAWYER
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.lawyer_img
import kotlinx.android.synthetic.main.activity_details.lawyer_name_txt
import kotlinx.android.synthetic.main.activity_details.rate_txt
import kotlinx.android.synthetic.main.lawyer_list_item.*


class DetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(tool_bar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        intent.extras?.getParcelable<Lawyer>(LAWYER)?.let {
            Glide.with(this).load(it.avatar).into(lawyer_img)
            lawyer_name_txt.text = it.firstName
            lawyer_surname_txt.text = it.lastName
            speciality.text = it.speciality
            rate_txt.text = it.rate
            ratings.text = it.rating
            reviews.text = it.reviewCount
            rankings.text = it.ranking

            description.text = it.description
            another_info_txt.text = it.moreInfo
        }




//        val textShader: Shader = LinearGradient(
//            0, 0, width, textView.getTextSize(), intArrayOf(
//                Color.parseColor("#F97C3C"),
//                Color.parseColor("#FDB54E"),
//                Color.parseColor("#64B678"),
//                Color.parseColor("#478AEA"),
//                Color.parseColor("#8446CC")
//            ), null, Shader.TileMode.CLAMP
//        )
//        textView.getPaint().setShader(textShader)
    }
}
