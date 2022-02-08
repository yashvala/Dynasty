package com.example.dynasty

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model(
    @Expose
    @SerializedName("BackgroundImage")
    val backgroundImage: String,
    @Expose
    @SerializedName("CategoryId")
    val categoryId: Int,
    @Expose
    @SerializedName("BusinessName")
    val businessName: String
)