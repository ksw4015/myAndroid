package com.sample.data.repository

import android.util.Log
import com.sample.data.datasource.remote.MoviesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : BaseRepository() {

    suspend fun getBoxOfficeMovies(targetDt: String, weekGb: String? = null, repNationCd: String? = null) = flow {
        emit(getResult { moviesRemoteDataSource.getBoxOfficeMovies(targetDt, weekGb, repNationCd) })
    }.catch {
        emit(Resource.error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}