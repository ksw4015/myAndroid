package com.sample.data.repository

import android.util.Log
import retrofit2.Response
import java.lang.Exception

/**
 * Network 응답용 Resource data class
 * 모델과 함께 상태도 관리
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {   // T는 Retrofit Service의 반환 객체?
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

open class BaseRepository {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            val errorBodyString = response.errorBody()?.string() ?: "Null"   // string() 메서드 실행 시 최초 1회떄만 온전히 반환 이후 실행시 Empty String 반환

            Log.d("BaseRepo", "${response.code()}, ${response.body()}, $errorBodyString")
            // Only OK response (Code = 200 ~ 300) 상태코드 200인 경우에만 isSuccessful true
            if(response.isSuccessful) {
                val body = response.body()
                if (body != null && response.code() == STATUS_SUCCESS) return Resource.success(body)
            }

            return Resource.error(errorBodyString)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }

    companion object {
        const val STATUS_SUCCESS = 200
        const val STATUS_TOKEN_EXPIRED = 401
        const val STATUS_NOT_FOUND = 404
        const val STATUS_INTERNAL_SERVER_ERROR = 500
    }

}