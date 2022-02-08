package com.example.dynasty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynasty.Models.Service
import com.example.dynasty.R

class ServiceAdapter(val serv: ArrayList<Service>, private val listener: OnClickListener) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvServiceTitle: TextView = itemView.findViewById(R.id.tvServiceTitle)
        var tvServiceDescription: TextView = itemView.findViewById(R.id.tvServiceDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.services_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = serv.get(position)

        holder.tvServiceTitle.text = service.service
        holder.tvServiceDescription.text = service.description

        holder.tvServiceTitle.setOnClickListener {
            listener.onClick(position)
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

    override fun getItemCount(): Int {
        return serv.size
    }
}