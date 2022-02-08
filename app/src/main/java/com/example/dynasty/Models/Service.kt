package com.example.dynasty.Models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Service(
    @SerializedName("BusinessServiceId")
    @Expose
    val businessServiceId: Int,

    @SerializedName("BusinessId")
    @Expose
    val businessId: Int,

    @SerializedName("Service")
    @Expose
    val service: String,

    @SerializedName("Description")
    @Expose
    val description: String,

    @SerializedName("IsActive")
    @Expose
    val isActive: Boolean
)

