package com.example.dynasty.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dynasty.Fragments.BusinessFragment
import com.example.dynasty.Fragments.HomeFragment
import com.example.dynasty.R

class SelectorActivity : AppCompatActivity() {

   /* lateinit var categoryFragment: Fragment
    lateinit var businessFragment: Fragment*/
    lateinit var imgHome: ImageView
    lateinit var imgBee: ImageView
    lateinit var imgBusiness: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector)

       /* categoryFragment = HomeFragment()
        businessFragment = BusinessFragment(0)

        if (categoryFragment.isVisible) {
            imgBusiness.setImageResource(R.drawable.ic_businesscard)
        }

        if (businessFragment.isVisible) {
        }
*/

            addFragment(HomeFragment())

        imgHome = findViewById(R.id.imgHome)
        imgBee = findViewById(R.id.imgBee)
        imgBusiness = findViewById(R.id.imgBusiness)

        imgHome.setOnClickListener {
            replaceFragment(HomeFragment())
            imgHome.setImageResource(R.drawable.ic_home)
            imgBusiness.setImageResource(R.drawable.ic_businesscard)
        }
        imgBee.setOnClickListener {
            replaceFragment(HomeFragment())
        }
        imgBusiness.setOnClickListener {
            replaceFragment(BusinessFragment(0))
            imgHome.setImageResource(R.drawable.ic_home_white)
            imgBusiness.setImageResource(R.drawable.ic_businesscard_color)
        }

        /*var bottomNav: BottomNavigationView = findViewById(R.id.navigationView)
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_bee -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_business -> {
                    replaceFragment(BusinessFragment(0))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }*/
    }

    public fun addFragment(fragment: Fragment) {
        val tag = fragment.javaClass.name
        supportFragmentManager.backStackEntryCount
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    public fun replaceFragment(fragment: Fragment) {
        val tag = fragment.javaClass.name
//        if (fragment is HomeFragment) {
//            supportFragmentManager.popBackStack(tag, POP_BACK_STACK_INCLUSIVE)
//        } else {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        //}
    }

}