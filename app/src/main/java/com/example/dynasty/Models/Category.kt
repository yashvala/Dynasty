package com.example.dynasty.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("CategoryId")
    @Expose
    val categoryId: Int,

    @SerializedName("CategoryName")
    @Expose
    val categoryName: String,

    @SerializedName("CategoryLogo")
    @Expose
    val categoryLogo: String,

    @SerializedName("IsActive")
    @Expose
    val isActive: Boolean,

    @SerializedName("BusinessLink")
    @Expose
    val businessLink: Int,

    @SerializedName("CreatedBy")
    @Expose
    val createdBy: Int,

    @SerializedName("ModifiedBy")
    @Expose
    val modifiedBy: Int,

    @SerializedName("CreatedDate")
    @Expose
    val createdDate: String,

    @SerializedName("ModifiedDate")
    @Expose
    val modifiedDate: String,

    @SerializedName("TotalRecords")
    @Expose
    val totalRecords: Int,

    @SerializedName("File")
    @Expose
    val file: String
)