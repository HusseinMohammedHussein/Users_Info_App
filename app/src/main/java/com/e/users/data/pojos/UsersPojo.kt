package com.e.users.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hussein on 4/15/2021
 */

data class UsersPojo(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("address") @Expose val address: Address,
    @SerializedName("phone") @Expose val phone: String,
    @SerializedName("website") @Expose val website: String,
    @SerializedName("username") @Expose val username: String,
    @SerializedName("company") @Expose val company: Company
) {
    data class Address(
        @SerializedName("city") @Expose val city: String
    )

    data class Company(
        @SerializedName("name") @Expose val name: String
    )
}


