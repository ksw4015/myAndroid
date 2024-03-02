package com.sample.data.datasource.remote

import com.sample.data.network.MoviesApi
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
){

    suspend fun getBoxOfficeMovies(targetDt: String, weekGb: String? = null, repNationCd: String? = null)
        = moviesApi.getBoxOfficeMovies(targetDt = targetDt, weekGb = weekGb, repNationCd = repNationCd)

}