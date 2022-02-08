package com.example.dynasty.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynasty.Adapter.SearchBusinessAdapter
import com.example.dynasty.Models.BusinessData
import com.example.dynasty.Models.Model
import com.example.dynasty.Network.ApiClient
import com.example.dynasty.R
import com.example.dynasty.checkConnectivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchBusinessFragment : Fragment() {

    lateinit var searchSymbolBusiness: EditText
    val businessesList = ArrayList<Model>()
    lateinit var busSearchRecycle: RecyclerView
    lateinit var searchBusinessAdapter: SearchBusinessAdapter
    lateinit var SearchBusinessProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        if (!checkConnectivity(context!!)) {
*/
            searchSymbolBusiness = view.findViewById(R.id.searchBusinessText)
            busSearchRecycle = view.findViewById(R.id.busSearchRecycle)
            SearchBusinessProgress = view.findViewById(R.id.SearchBusinessProgress)

            val lay: RecyclerView.LayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            busSearchRecycle.setLayoutManager(lay)

            searchBusinessAdapter = SearchBusinessAdapter(businessesList, this,
                object : SearchBusinessAdapter.onClickListner {
                    override fun onClick(position: Int, bModel: Model) {
                        var bid = bModel.businessId
                        val fragment: Fragment = BusinessDetailFragment(bModel)
                        val tag = fragment.javaClass.name
                        val fragmentManager = activity!!.supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.container, fragment)
                        fragmentTransaction.addToBackStack(tag)
                        fragmentTransaction.commit()
                    }

                })
            busSearchRecycle.setAdapter(searchBusinessAdapter)

            getData()

            searchSymbolBusiness.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    searchBusinessAdapter.filter.filter(p0)
                }
            })

        }

    private fun getData() {
        val call: Call<BusinessData> = ApiClient.getBusinessClient.getBackground(0)
        call.enqueue(object : Callback<BusinessData> {
            override fun onResponse(
                call: Call<BusinessData>,
                response: Response<BusinessData>
            ) {
                SearchBusinessProgress.visibility=View.GONE
                businessesList.clear()
                businessesList.addAll(response.body()!!.businessdata)
                searchBusinessAdapter.notifyDataSetChanged1()
            }

            override fun onFailure(call: Call<BusinessData>, t: Throwable) {
            }
        })
    }

}