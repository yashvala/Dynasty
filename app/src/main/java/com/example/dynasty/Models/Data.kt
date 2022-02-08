package com.example.dynasty.Models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Data")
    val data: List<Category>
)
