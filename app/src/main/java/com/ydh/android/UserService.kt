package com.ydh.android

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        private val interceptor: HttpLoggingInterceptor by lazy {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        private val httpClient : OkHttpClient by lazy {
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
        fun getService(): UserService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            return retrofit.create(UserService::class.java)
        }
    }
}