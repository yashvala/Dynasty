package com.example.dynasty.Fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynasty.Adapter.BannerAdapter
import com.example.dynasty.Adapter.ServiceAdapter
import com.example.dynasty.Models.*
import com.example.dynasty.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup


class BusinessDetailFragment(mModel: Model) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    val  images= ArrayList<Image>()
    val  services = ArrayList<Service>()
    val  tags = ArrayList<Tag>()
    val  numbers = ArrayList<ContactNumber>()

    var bid = mModel

    var bussinessId = bid.businessId
    lateinit var businessName: TextView
    lateinit var businessAddress: TextView
    lateinit var tvAboutUsDetail: TextView
    lateinit var businessDetailsCategoryName: TextView
    lateinit var companyLogo: ImageView
    lateinit var imgService: ImageView
    lateinit var imgAboutBg: ImageView
    lateinit var imgChip: ImageView
    lateinit var logoFb: ImageView
    lateinit var logoTwitter: ImageView
    lateinit var logoInsta: ImageView
    lateinit var logoLoc: ImageView
    lateinit var logoMail: ImageView
    lateinit var logoMsg: ImageView
    lateinit var logoCall: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var recycleServices: RecyclerView
    lateinit var bannerAdapter: BannerAdapter
    lateinit var serviceAdapter: ServiceAdapter
    lateinit var chipGroup: ChipGroup
    lateinit var detailBusinessProgress: ProgressBar

    lateinit var cardViewService:CardView
    lateinit var cardViewChip:CardView

    lateinit var latitudeob: String
    lateinit var longitudeob: String
    lateinit var numbernoobject: String
    var REQUEST_CALL = 123
    var REQUEST_LOCATION = 456

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        businessName = view.findViewById(R.id.businessName)
        businessAddress = view.findViewById(R.id.businessAddress)
        tvAboutUsDetail = view.findViewById(R.id.tvAboutUsDetail)
        companyLogo = view.findViewById(R.id.companyLogo)
        imgService = view.findViewById(R.id.imgService)
        imgAboutBg = view.findViewById(R.id.imgAboutBg)
        imgChip = view.findViewById(R.id.imgChip)
        businessDetailsCategoryName = view.findViewById(R.id.businessDetailsCategoryName)
        logoFb = view.findViewById(R.id.logoFb)
        logoTwitter = view.findViewById(R.id.logoTwitter)
        logoInsta = view.findViewById(R.id.logoInsta)
        logoLoc = view.findViewById(R.id.logoLoc)
        logoMail = view.findViewById(R.id.logoMail)
        logoMsg = view.findViewById(R.id.logoMsg)
        logoCall = view.findViewById(R.id.logoCall)
        cardViewService = view.findViewById(R.id.cardViewService)
        cardViewChip = view.findViewById(R.id.cardViewChip)
        chipGroup = view.findViewById(R.id.chipGroup)
        detailBusinessProgress = view.findViewById(R.id.detailBusinessProgress)

        recyclerView = view.findViewById(R.id.recycleImages)
        recycleServices = view.findViewById(R.id.recycleServices)


        images.addAll(bid.images)
        services.addAll(bid.services)
        tags.addAll(bid.tags)
        numbers.addAll(bid.contactNumbers)

/*
        var no = number.get(0).contactNo
*/

        var fbob = bid.facebookLink
        var igob = bid.instagramLink
        var twitterob = bid.twitterLink
        var mailob = bid.email
        latitudeob = bid.latitude
        longitudeob = bid.longitude


        val lay : RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = lay

        val serviceRecycler : RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycleServices.layoutManager = serviceRecycler


        if(images.size!=0){
            recyclerView.visibility =View.VISIBLE
        }
        bannerAdapter = BannerAdapter(images,object :BannerAdapter.OnClickListener{
            override fun onClick(position: Int) {}
        })
        recyclerView.setAdapter(bannerAdapter)

        if (services.size != 0){
            cardViewService.visibility = View.VISIBLE

            Glide.with(this)
                .load(bid.backgroundImage)
                .placeholder(R.drawable.cardviewplaceholder)
                .error(R.drawable.cardviewplaceholder)
                .into(imgService)
            serviceAdapter = ServiceAdapter(services,object :ServiceAdapter.OnClickListener{
                override fun onClick(position: Int) {}
            })
            recycleServices.setAdapter(serviceAdapter)
        }


        if (tags.size!=0) {
            cardViewChip.visibility = View.VISIBLE
            setCustomTag(context!!,chipGroup,tags)

            Glide.with(this)
                .load(bid.backgroundImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.cardviewplaceholder)
                .into(imgChip)
        }

        if (fbob != null) {
            logoFb.visibility = View.VISIBLE
            logoFb.setOnClickListener {
                val facebookIntent = Intent(Intent.ACTION_VIEW)
                val facebookUrl: String = fbob
                facebookIntent.data = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl)
                startActivity(facebookIntent)
            }
        }

        if (igob!= null) {
            logoInsta.visibility = View.VISIBLE
            logoInsta.setOnClickListener {
                Toast.makeText(activity?.applicationContext, "Instagram", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(igob)
                intent.setPackage("com.instagram.android")
                startActivity(intent)
            }
        }

        if (twitterob != null) {
            logoTwitter.visibility = View.VISIBLE
            logoTwitter.setOnClickListener {
                val twitterIntent = Intent(Intent.ACTION_VIEW)
                val twitterUrl: String = twitterob
                twitterIntent.data = Uri.parse(twitterob)
                startActivity(twitterIntent)
            }
        }

        if (numbers.size != 0){
            numbernoobject = numbers.get(0).contactNo
            logoMsg.visibility=View.VISIBLE
            logoMsg.setOnClickListener {
                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.type = "vnd.android-dir/mms-sms"
                smsIntent.putExtra("address", numbernoobject)
                smsIntent.putExtra("sms_body", "Your Feedback / Query")
                startActivity(smsIntent)
            }
        }

        if (numbers.size != 0) {
            numbernoobject = numbers.get(0).contactNo
            logoCall.visibility = View.VISIBLE
            logoCall.setOnClickListener {
                makeCall()
            }
        }

        if (mailob != null) {
            logoMail.visibility = View.VISIBLE
            logoMail.setOnClickListener {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + mailob))
                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject")
                intent.putExtra(Intent.EXTRA_TEXT, "your_text")
                startActivity(intent)
            }
        }

        if ( latitudeob!= null && longitudeob!=null) {
            logoLoc.visibility = View.VISIBLE
            logoLoc.setOnClickListener {
                loadMap()
            }
        }

        if (businessName!=null){
            businessName.text = bid.businessName
            detailBusinessProgress.visibility=View.GONE
        }
        businessAddress.setText(bid.address)
        tvAboutUsDetail.setText(bid.about)
        businessDetailsCategoryName.text = bid.categoryName

        Glide.with(this)
            .load(bid.businessLogo)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(companyLogo)

        Glide.with(this)
            .load(bid.backgroundImage)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.cardviewplaceholder)
            .into(imgAboutBg)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )

    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall();
            } else {
                Toast.makeText(context, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == REQUEST_LOCATION){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadMap();
            } else {
                Toast.makeText(context, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun makeCall() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf<String>(Manifest.permission.CALL_PHONE),
                REQUEST_CALL
            )
        } else {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + numbernoobject)
            startActivity(intent)
        }
    }

    private fun loadMap() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION
            )
        } else {
            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination",  latitudeob + "," + longitudeob)
            val url = builder.build().toString()
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun setCustomTag(context: Context, chipGroup: ChipGroup, tagList: List<Tag>) {
        chipGroup.removeAllViews();
        for (index in tagList.indices) {
            val tagName = tagList.get(index).tagName
            val chip: Chip = Chip(context)
            val paddingDp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5F, context.getResources().getDisplayMetrics()).toInt()
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            if (!tagName.equals("null", ignoreCase = true)) {
                chip.setText(tagName);
            }
            chip.setTextSize(12f);
            chip.setTextColor(ContextCompat.getColor(context, R.color.bgcolor));
            chip.isAllCaps = true
            chip.letterSpacing =0.17f

            val chipDrawable: ChipDrawable = chip.getChipDrawable() as ChipDrawable
            chipDrawable.setChipBackgroundColor(
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
            );
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.chipcolor))
            chip.setChipDrawable(chipDrawable)
            chip.setCloseIconEnabled(false)
            chip.setCheckedIconVisible(false)
            chip.setCheckable(true)
            chip.setChipIconEnabled(false)
            val finalIndex = index
            chipGroup.addView(chip)
        }
    }
}