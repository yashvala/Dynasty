package com.example.dynasty.Models

import com.google.gson.annotations.SerializedName

data class BusinessData(
    @SerializedName("Data")
    val businessdata: List<Model>
)
