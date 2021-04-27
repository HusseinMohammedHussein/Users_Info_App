package com.e.users.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Created by Hussein_Mohammad on 4/25/2021.

data class PhotosPojo(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("thumbnailUrl") @Expose val thumbnailUrl: String
)
