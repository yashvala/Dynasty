package com.example.dynasty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynasty.Fragments.HomeFragment
import com.example.dynasty.Models.Category
import com.example.dynasty.R

class HomeAdapter(val categoryList: ArrayList<Category>, private val context: HomeFragment,private val listener:OnClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var backgroundImage : ImageView = itemView.findViewById(R.id.imgCard)
        var businessName : TextView= itemView.findViewById(R.id.titleCard)
        var categoryId : TextView= itemView.findViewById(R.id.idCard)
        var cardViewCon : ConstraintLayout= itemView.findViewById(R.id.cardViewCon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = categoryList.get(position)

            Glide.with(context)
                .load((dataModel.categoryLogo))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.backgroundImage)

        holder.businessName.setText(dataModel.categoryName)
        holder.categoryId.setText(dataModel.businessLink.toString())

        val cate = dataModel.categoryId
        val link = dataModel.businessLink
        holder.cardViewCon.setOnClickListener {
            listener.onClick(position,cate,link) }
    }
    interface OnClickListener{
        fun onClick(position:Int,id:Int,link:Int)
    }

    fun clear() {
        categoryList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: ArrayList<Category>?) {
        list?.let { categoryList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}