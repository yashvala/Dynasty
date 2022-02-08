package com.example.dynasty.Models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("BusinessImageId")
    @Expose
    val businessImageId: Int,

    @SerializedName("ImageURL")
    @Expose
    val imageURL: String,

    @SerializedName("IsActive")
    @Expose
    val isActive: Boolean
)
