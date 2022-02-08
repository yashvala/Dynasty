package com.example.dynasty.Models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Tag(
    @SerializedName("BusinessTagId")
    @Expose
    val businessTagId: Int,

    @SerializedName("BusinessId")
    @Expose
    val businessId: Int,

    @SerializedName("TagName")
    @Expose
    val tagName: String,

    @SerializedName("IsActive")
    @Expose
    val isActive: Boolean
)
