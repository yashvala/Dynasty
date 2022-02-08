package com.example.dynasty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynasty.Fragments.BusinessFragment
import com.example.dynasty.Models.Model
import com.example.dynasty.R

class  BusinessAdapter(val businessList: ArrayList<Model>, private val context: BusinessFragment, private val listener: onClickListner) :RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgBackground: ImageView = view.findViewById(R.id.imgBackground)
        var rightView: View = view.findViewById(R.id.viewRight)
        var leftView: View = view.findViewById(R.id.viewLeft)
        var businessNameLeft: TextView = view.findViewById(R.id.businessNameLeft)
        var businessCityLeft: TextView = view.findViewById(R.id.businessCityLeft)
        var businessNameRight: TextView = view.findViewById(R.id.businessNameRight)
        var businessCityRight: TextView = view.findViewById(R.id.businessCityRight)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.business_recyclerview_item, parent, false)
        return BusinessAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Model = businessList.get(position)
        Glide.with(context)
            .load(Model.businessLogo)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.imgBackground)

        if (position % 2 == 0) {
            holder.rightView.setVisibility(View.VISIBLE)
            holder.leftView.setVisibility(View.INVISIBLE)
            holder.businessNameLeft.setVisibility(View.INVISIBLE)
            holder.businessCityLeft.setVisibility(View.INVISIBLE)
            holder.businessNameRight.setVisibility(View.VISIBLE)
            holder.businessCityRight.setVisibility(View.VISIBLE)
            holder.businessNameRight.setText(Model.businessName)
            holder.businessCityRight.setText(Model.city)
        } else {
            holder.leftView.setVisibility(View.VISIBLE)
            holder.rightView.setVisibility(View.INVISIBLE)
            holder.businessNameRight.setVisibility(View.INVISIBLE)
            holder.businessCityRight.setVisibility(View.INVISIBLE)
            holder.businessNameLeft.setVisibility(View.VISIBLE)
            holder.businessCityLeft.setVisibility(View.VISIBLE)
            holder.businessNameLeft.setText(Model.businessName)
            holder.businessCityLeft.setText(Model.city)
        }

        holder.itemView.setOnClickListener {
            listener.onClick(position,  Model )
        }
    }

    interface onClickListner{
        fun onClick(position: Int, bModel:Model)
    }

    override fun getItemCount(): Int {
        return businessList.size
    }
}

