package com.e.users.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlbumsPojo(
    @SerializedName("id") @Expose  val id: Int,
    @SerializedName("title") @Expose  val title: String
)
