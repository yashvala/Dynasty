package com.example.dynasty.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ContactNumber(
    @SerializedName("BusinessContactNoId")
    @Expose
    val businessContactNoId: Int,

    @SerializedName("BusinessId")
    @Expose
    val businessId: Int,

    @SerializedName("ContactNo")
    @Expose
    val contactNo: String,

    @SerializedName("IsActive")
    @Expose
    val isActive: Boolean
)
