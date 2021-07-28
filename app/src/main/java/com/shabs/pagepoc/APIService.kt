package com.shabs.pagepoc

import com.shabs.pagepoc.models.APIResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET("/")
    suspend fun getListData(
        @Query("page") pageNumber: Int,
        @Query("s") moviewName: String,
        @Query("apikey") apiKey: String
    ): Response<APIResponse>

    companion object {

        val BASE_URL = "https://www.omdbapi.com/"

        fun getApiService(): APIService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }

    }
}