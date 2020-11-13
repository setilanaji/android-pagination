package com.ydh.android

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api/users")
    fun getAllUser(
        @Query("page")
        page: Int,
        @Query("per_page")
        perPage: Int
    ): Single<UserResponse>

    companion object {
        fun getService(): UserService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(UserService::class.java)
        }
    }
}