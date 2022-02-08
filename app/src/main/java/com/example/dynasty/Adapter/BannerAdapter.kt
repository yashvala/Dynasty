package com.example.dynasty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynasty.Models.Image
import com.example.dynasty.R

class BannerAdapter(val images: ArrayList<Image>,private val listener: OnClickListener) :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var backgroundImage: ImageView = itemView.findViewById(R.id.imgCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = images.get(position)

        Glide.with(holder.itemView.context)
            .load((dataModel.imageURL))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.backgroundImage)
        holder.backgroundImage.setOnClickListener {
            listener.onClick(position)
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}