package com.example.dynasty.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.dynasty.Adapter.HomeAdapter
import com.example.dynasty.Models.Category
import com.example.dynasty.Models.Data
import com.example.dynasty.Network.ApiClient
import com.example.dynasty.NetworkConnection
import com.example.dynasty.R
import com.example.dynasty.activities.SelectorActivity
import com.example.dynasty.checkConnectivity
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    val categoryList = ArrayList<Category>()
    lateinit var recyclerView: RecyclerView
    lateinit var homeAdapter: HomeAdapter
    lateinit var swipeRefresh:SwipeRefreshLayout
    lateinit var homeProgress:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



/*
        if (!checkConnectivity(context!!)) {
*/
            recyclerView = view.findViewById(R.id.homeRecycleView)
            swipeRefresh = view.findViewById(R.id.swipeRefresh)
            homeProgress = view.findViewById(R.id.homeProgress)

            swipeRefresh.setOnRefreshListener(OnRefreshListener {
                getData()
            })
            swipeRefresh.setColorSchemeResources(
                R.color.fontcolor
            );

            /*val networkConnection =NetworkConnection(activity!!.applicationContext)
        networkConnection.observe(this, Observer { isConnected->
            if (isConnected){
                nointernet.visibility =View.GONE
                connect.visibility =View.VISIBLE
            }
            else{
                nointernet.visibility =View.VISIBLE
                connect.visibility =View.GONE
            }
        })*/

            searchBusiness.setOnClickListener {
                val fragment: Fragment = SearchCategoryFragment()
                val fragmentManager =
                    activity!!.supportFragmentManager
                val fragmentTransaction =
                    fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, fragment)
                fragmentTransaction.addToBackStack(tag)
                fragmentTransaction.commit()
            }

            val lay: RecyclerView.LayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.setLayoutManager(lay)

            homeAdapter =
                HomeAdapter(categoryList, this, object : HomeAdapter.OnClickListener {
                    override fun onClick(position: Int, id: Int, link: Int) {
                        if (link > 0) {
                            val fragment: Fragment = BusinessFragment(id)
                            val fragmentManager = activity!!.supportFragmentManager
                            val fragmentTransaction = fragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.container, fragment)
                            fragmentTransaction.addToBackStack(tag)
                            fragmentTransaction.commit()
                        }
                    }
                })

            recyclerView.setAdapter(homeAdapter)

            getData()
        }

    private fun getData() {
        val call: Call<Data> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                homeProgress.visibility = View.GONE
                swipeRefresh.isRefreshing =false
                categoryList.clear()
                categoryList.addAll(ArrayList(response.body()?.data))
                homeAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                swipeRefresh.isRefreshing =false
            }
        })
    }


}