package com.ijikod.lawyer_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ijikod.lawyer_app.Data.Model.Lawyer
import com.ijikod.lawyer_app.R
import javax.xml.transform.ErrorListener

class Adapter(val data: List<Lawyer>, val listener: LawyerClick) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lawyer_list_item, parent, false)

        return  ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lawyer = data.get(position)
        with(holder){
            bind(lawyer)

            itemView.setOnClickListener {
                listener.onLawyerClick(lawyer)
            }
        }
    }


    interface LawyerClick{
        fun onLawyerClick(lawyer: Lawyer)
    }






    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
        val lawyerImage = viewItem.findViewById<ImageView>(R.id.lawyer_img)
        val name = viewItem.findViewById<TextView>(R.id.lawyer_name_txt)
        val specialty = viewItem.findViewById<TextView>(R.id.specliaty)
        val rate = viewItem.findViewById<TextView>(R.id.rate_txt)
        val ratings = viewItem.findViewById<TextView>(R.id.ratings_txt)
        val verifiedImg = viewItem.findViewById<View>(R.id.check_img)

        fun bind(lawyer: Lawyer){
            Glide.with(lawyerImage.context).load(lawyer.avatar).into(lawyerImage)
            name.text = "${lawyer.firstName} ${lawyer.lastName}"
            specialty.text = "${lawyer.speciality}"
            rate.text = "${lawyer.rate}"
            ratings.text = "${lawyer.rating}"
            if (lawyer.verified == "0")
                verifiedImg.visibility = View.GONE


        }


    }


}