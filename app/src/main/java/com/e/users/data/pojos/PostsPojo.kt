package com.e.users.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hussein on 4/10/2021
 */

data class PostsPojo(
    @SerializedName("userId") @Expose val userId: Int,
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("body") @Expose val body: String
)
