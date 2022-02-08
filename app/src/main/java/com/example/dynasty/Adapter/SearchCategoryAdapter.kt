package com.example.dynasty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynasty.Fragments.SearchCategoryFragment
import com.example.dynasty.Models.Category
import com.example.dynasty.R

class SearchCategoryAdapter(val categoryList: ArrayList<Category>, private val context: SearchCategoryFragment, private val listener: SearchCategoryAdapter.OnClickListener) : RecyclerView.Adapter<SearchCategoryAdapter.CategorySearchHolder>(),Filterable{
    var fullList: ArrayList<Category> = ArrayList()

    init {
        fullList = categoryList.clone() as ArrayList<Category>
    }

    public fun notifyDataSetChanged1(){
        fullList = ArrayList(categoryList)
        notifyDataSetChanged()
    }

    class CategorySearchHolder(view: View) : RecyclerView.ViewHolder(view) {

        var categoryName: TextView = itemView.findViewById(R.id.searchCategoryName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySearchHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_category_recyclerview_item, parent, false)
        return CategorySearchHolder(view)
    }

    override fun onBindViewHolder(holder: CategorySearchHolder, position: Int) {
        val category = categoryList.get(position)

        holder.categoryName.text = category.categoryName
        val cate = category.categoryId
        val link = category.businessLink
        holder.categoryName.setOnClickListener {
            listener.onClick(position,cate,link) }
    }

    interface OnClickListener{
        fun onClick(position:Int,id:Int,link:Int)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<Category> = ArrayList()
                if (constraint == null || constraint.length === 0) {
                    filteredList.addAll(fullList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (item in fullList) {
                        if (item.categoryName.toLowerCase().contains(filterPattern)) {
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
                categoryList.clear()
                categoryList.addAll(results!!.values as List<Category>)
                notifyDataSetChanged()
            }

        }
    }

}