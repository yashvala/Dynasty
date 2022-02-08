package com.example.dynasty.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynasty.Adapter.SearchCategoryAdapter
import com.example.dynasty.Models.Category
import com.example.dynasty.Models.Data
import com.example.dynasty.Network.ApiClient
import com.example.dynasty.R
import com.example.dynasty.checkConnectivity
import kotlinx.android.synthetic.main.search_category_recyclerview_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchCategoryFragment : Fragment() {

    val categoryList = ArrayList<Category>()
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var searchText: EditText
    lateinit var searchAdapter: SearchCategoryAdapter
    lateinit var searchCategoryProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        if (!checkConnectivity(context!!)) {
*/
            recyclerViewSearch = view.findViewById(R.id.searchRecycle)
            searchText = view.findViewById(R.id.searchText)
            searchCategoryProgress = view.findViewById(R.id.searchCategoryProgress)

            val lay: RecyclerView.LayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerViewSearch.setLayoutManager(lay)

            searchAdapter = SearchCategoryAdapter(
                categoryList,
                this,
                object : SearchCategoryAdapter.OnClickListener {
                    override fun onClick(position: Int, id: Int, link: Int) {
                        if (link > 0) {
                            val fragment: Fragment = BusinessFragment(id)
                            val tag = fragment.javaClass.name
                            val fragmentManager = activity!!.supportFragmentManager
                            val fragmentTransaction = fragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.container, fragment)
                            fragmentTransaction.addToBackStack(tag)
                            fragmentTransaction.commit()
                        }
                    }
                })
            recyclerViewSearch.setAdapter(searchAdapter)

            searchText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    searchAdapter.filter.filter(p0)
                }
            })

            getData()
        }

    private fun getData() {
        val call: Call<Data> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                searchCategoryProgress.visibility=View.GONE
                categoryList.clear()
                categoryList.addAll(ArrayList(response.body()?.data))
                searchAdapter.notifyDataSetChanged1()
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
            }
        })
    }
}