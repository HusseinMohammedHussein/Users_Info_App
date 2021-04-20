package com.e.users.data.pojos

import com.squareup.moshi.Json

data class PostCommentsPojo(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "body") val body: String
)
