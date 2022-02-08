package com.example.dynasty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynasty.Fragments.SearchBusinessFragment
import com.example.dynasty.Models.Model
import com.example.dynasty.R

class SearchBusinessAdapter(
    private val businessList: ArrayList<Model>, private val context: SearchBusinessFragment, private val listener: onClickListner
) : RecyclerView.Adapter<SearchBusinessAdapter.BusinessSearchHolder>(), Filterable {

    var fullList: ArrayList<Model> = ArrayList()

    init {
        fullList = businessList.clone() as ArrayList<Model>
    }

    public fun notifyDataSetChanged1(){
        fullList = ArrayList(businessList)
        notifyDataSetChanged()
    }

    class BusinessSearchHolder(view: View) : RecyclerView.ViewHolder(view) {

        var businessName: TextView = itemView.findViewById(R.id.searchBusinessName)
        var businessCityName : TextView = itemView.findViewById(R.id.businessCityName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessSearchHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.business_search_recyclerview_item, parent, false)
        return BusinessSearchHolder(view)
    }

    override fun onBindViewHolder(holder: BusinessSearchHolder, position: Int) {
        val business = businessList.get(position)

        holder.businessName.text = business.businessName
        holder.businessCityName.setText(business.city)

        val businessId = business.businessId

        holder.itemView.setOnClickListener {
            listener.onClick(position, business )
        }

    }
    interface onClickListner{
        fun onClick(position: Int,bModel:Model)
    }

    override fun getItemCount(): Int {
        return businessList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<Model> = ArrayList()
                if (constraint == null || constraint.length === 0) {
                    filteredList.addAll(fullList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (item in fullList) {
                        if (item.businessName.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                businessList.clear()
                businessList.addAll(results!!.values as List<Model>)
                notifyDataSetChanged()
            }

        }
    }

}