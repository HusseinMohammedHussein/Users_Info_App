package com.e.users.data.pojos

import com.squareup.moshi.Json

/**
 * Created by Hussein on 4/15/2021
 */

data class UsersPojo(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "email")
    val email: String
)
