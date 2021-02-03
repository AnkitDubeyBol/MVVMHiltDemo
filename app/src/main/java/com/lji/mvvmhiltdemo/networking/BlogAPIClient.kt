package com.lji.mvvmhiltdemo.networking

import retrofit2.http.GET

interface BlogAPIClient {
    @GET("blogs")
    suspend fun get() : List<BlogNetworkEntity>
}