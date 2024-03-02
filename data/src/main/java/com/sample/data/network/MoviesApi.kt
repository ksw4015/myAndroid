package com.sample.data.network

import com.sample.data.model.movies.MoviesApiModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    companion object {
        const val MOVIE_API_KEY = "195ddf7a451d1c7d811d40ea5e0a9cd2"
    }

    @GET("searchWeeklyBoxOfficeList.json")
    suspend fun getBoxOfficeMovies(
        @Query("key") key: String = MOVIE_API_KEY,
        @Query("targetDt") targetDt: String,
        @Query("weekGb") weekGb: String? = null,
        @Query("repNationCd") repNationCd: String? = null
    ) : Response<MoviesApiModels>

}