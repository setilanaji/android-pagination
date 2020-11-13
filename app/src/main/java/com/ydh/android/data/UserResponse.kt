package com.ydh.android.data

import com.google.gson.annotations.SerializedName
import com.ydh.android.model.UserModel

data class UserResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val result: List<UserModel>

)