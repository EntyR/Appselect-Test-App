package com.enticity.data.datasource.room

import com.enticity.data.datasource.room.room_model.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/svc/movies/v2/reviews/search.json")
    suspend fun getMovie(
        @Query("offset") offset: Int,
        @Query("api-key") key: String
    ): MovieDto
}