package com.e.users.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostCommentsPojo(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("body") @Expose val body: String
)
