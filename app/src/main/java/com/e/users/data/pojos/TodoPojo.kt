package com.e.users.data.pojos

import com.squareup.moshi.Json

data class TodoPojo(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "completed") val completed: Boolean
)
