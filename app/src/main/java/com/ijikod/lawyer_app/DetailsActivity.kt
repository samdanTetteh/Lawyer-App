package com.ijikod.lawyer_app

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.Utilities.LAWYER
import kotlinx.android.synthetic.main.activity_details.*


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


        val animDrawable = schedule_btn.background as AnimationDrawable
        animDrawable.setExitFadeDuration(1000)
        animDrawable.start()

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        val paint: TextPaint = consultation_txt.getPaint()
        val width: Float = paint.measureText(getString(R.string._1st_free_consultation_available))


        val textShader: Shader = LinearGradient(0f,0f,
             width, consultation_txt.getTextSize(),  ContextCompat.getColor(this,R.color.colorGradientStart), ContextCompat.getColor(this,R.color.colorGradientEnd), Shader.TileMode.CLAMP)
        consultation_txt.getPaint().setShader(textShader)
    }
}
