package com.tegarap.lokaloops.rest

import com.tegarap.lokaloops.models.ListHistory
import com.tegarap.lokaloops.models.ListItemResponse
import com.tegarap.lokaloops.models.UserResponse
import retrofit2.Call
import retrofit2.http.*


interface InterfacePoint {

    @GET("testapi")
    fun login(
        @Query("username") username:String,
        @Query("password") password:String
    ): Call<UserResponse>

//    @FormUrlEncoded
//    @POST("testapi")
//    fun login(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ): Call<UserResponse>

    @GET("home")
    fun listItem(): Call<ListItemResponse>

    @GET("riwayat")
    fun listHistory(): Call<ListHistory>
}