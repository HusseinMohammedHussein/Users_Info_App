package com.e.users.data.pojos

import com.squareup.moshi.Json

/**
 * Created by Hussein on 4/10/2021
 */

data class PostsPojo(
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String
)
