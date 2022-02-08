package com.example.dynasty.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dynasty.Adapter.BusinessAdapter
import com.example.dynasty.Models.BusinessData
import com.example.dynasty.Models.Model
import com.example.dynasty.Network.ApiClient
import com.example.dynasty.R
import com.example.dynasty.activities.SelectorActivity
import com.example.dynasty.checkConnectivity
import kotlinx.android.synthetic.main.fragment_business.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusinessFragment(id:Int): Fragment() {


    val modelList = ArrayList<Model>()
    lateinit var recyclerView: RecyclerView
    lateinit var businessAdapter: BusinessAdapter
    lateinit var businessProgress: ProgressBar
    lateinit var pagination: ProgressBar
    lateinit var businessSwiperefresh: SwipeRefreshLayout
    val catagory = id

   /* var isScrolling:Boolean = false
    var currentItems: Int = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let{
            it?.getSerializable("DATA")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        if (!checkConnectivity(context!!)) {
*/

        recyclerView = view.findViewById(R.id.businessRecycler)
        businessProgress = view.findViewById(R.id.businessProgress)
/*
        pagination = view.findViewById(R.id.pagination)
*/
        businessSwiperefresh = view.findViewById(R.id.businessSwiperefresh)

        businessSwiperefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            getBusinessData()
        })

        businessSwiperefresh.setColorSchemeResources(
            R.color.fontcolor);

        val lay:RecyclerView.LayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(lay)
        businessAdapter =
            BusinessAdapter(modelList, this@BusinessFragment, object : BusinessAdapter.onClickListner{
                override fun onClick(position: Int, bModel: Model) {
                    var bid =bModel.businessId
                    val fragment: Fragment = BusinessDetailFragment(bModel)
                    val tag = fragment.javaClass.name
                    val fragmentManager = activity!!.supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.container, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }

            })
        recyclerView.setAdapter(businessAdapter)

        /*recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = lay.childCount
                totalItems = lay.itemCount
            }
        })*/

        searchSymbolBusiness.setOnClickListener {
            val fragment: Fragment = SearchBusinessFragment()
            val fragmentManager =
                activity!!.supportFragmentManager
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        getBusinessData()
        }

    private fun getBusinessData() {
        val call: Call<BusinessData> = ApiClient.getBusinessClient.getBackground(catagory)
        call.enqueue(object : Callback<BusinessData> {
            override fun onResponse(call: Call<BusinessData>, response: Response<BusinessData>) {
                businessProgress.visibility = View.GONE
                businessSwiperefresh.isRefreshing =false
                modelList.clear()
                modelList.addAll(ArrayList(response.body()?.businessdata))
                businessAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<BusinessData>, t: Throwable) {
                businessSwiperefresh.isRefreshing =false
            }
        })
    }
}