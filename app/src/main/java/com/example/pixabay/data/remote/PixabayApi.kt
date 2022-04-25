package com.example.pixabay.data.remote

import com.example.pixabay.BuildConfig
import com.example.pixabay.data.remote.dto.SearchHitDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    fun searchHitsAsync(@Query("key") apiKey: String = BuildConfig.API_KEY, @Query("q") query: String): Deferred<SearchHitDto>
}