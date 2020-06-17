package com.ijikod.lawyer_app

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.TextPaint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.Utilities.LAWYER
import com.ijikod.lawyer_app.databinding.ActivityDetailsBinding
import com.ijikod.lawyer_app.ui.LawyersListViewModel
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    lateinit var viewModel : LawyersListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)


        viewModel = ViewModelProviders.of(this).get(LawyersListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.lawyerViewModel = viewModel
        viewModel.getLiveData().value = intent.getParcelableExtra(LAWYER)


        val animDrawable = schedule_btn.background as AnimationDrawable
        animDrawable.setExitFadeDuration(2000)
        animDrawable.start()


        val paint: TextPaint = consultation_txt.getPaint()
        val width: Float = paint.measureText(getString(R.string._1st_free_consultation_available))


        val textShader: Shader = LinearGradient(0f,0f,
             width, consultation_txt.getTextSize(),  ContextCompat.getColor(this,R.color.colorGradientStart), ContextCompat.getColor(this,R.color.colorGradientEnd), Shader.TileMode.CLAMP)
        consultation_txt.getPaint().setShader(textShader)
    }
}
