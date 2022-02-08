package com.example.dynasty.Models

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
    val businessName: String,
    @Expose
    @SerializedName("BusinessId")
    val businessId: Int,
    @Expose
    @SerializedName("BusinessLogo")
    val businessLogo: String,
    @Expose
    @SerializedName("Email")
    val email: String,
    @Expose
    @SerializedName("About")
    val about: String,
    @Expose
    @SerializedName("Latitude")
    val latitude: String,
    @Expose
    @SerializedName("Longitude")
    val longitude: String,
    @SerializedName("Address")
    @Expose
    val address: String,
    @SerializedName("City")
    @Expose
    val city: String,

    @SerializedName("PostCode")
    @Expose
    val postCode: String,

    @SerializedName("LinkedInLink")
    @Expose
    val linkedInLink: String,

    @SerializedName("TwitterLink")
    @Expose
    val twitterLink: String,

    @SerializedName("FacebookLink")
    @Expose
    val facebookLink: String,

    @SerializedName("InstagramLink")
    @Expose
    val instagramLink: String,

    @SerializedName("CategoryName")
    @Expose
    val categoryName: String,

    @SerializedName("CategoryLogo")
    @Expose
    val categoryLogo: String,

    @SerializedName("IsActive")
    @Expose
    val isActive: Boolean,

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

    @SerializedName("ContactNumbers")
    @Expose
    val contactNumbers: List<ContactNumber>,

    @SerializedName("Images")
    @Expose
    val images: List<Image>,

    @SerializedName("Services")
    @Expose
    val services: List<Service>,

    @SerializedName("Tags")
    @Expose
    val tags: List<Tag>,

    @SerializedName("TotalRecords")
    @Expose
    val totalRecords: Int,

    @SerializedName("BusinessLogoFile")
    @Expose
    val businessLogoFile: String,

    @SerializedName("BusinessBackgroundFile")
    @Expose
    val businessBackgroundFile: String
)

