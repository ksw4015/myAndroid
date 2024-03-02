package com.sample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sample.data.model.movies.MoviesWeekly
import com.sample.data.repository.MoviesRepository
import com.sample.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getBoxOfficeMovies(targetDt: String, weekGb: String? = null, repNationCd: String? = null) {
        viewModelScope.launch {
            _uiState.update { MainUiState.Loading }
            moviesRepository.getBoxOfficeMovies(targetDt, weekGb, repNationCd)
                .catch { result ->
                    _uiState.update { MainUiState.Error(result.message ?: "") }
                }
                .collect { result ->
                    _uiState.update {
                        if(result.status == Resource.Status.ERROR || result.data == null) {
                            MainUiState.Error(result.message ?: "Network Error!")
                        } else {
                            MainUiState.MovieWeekly(result.data!!.boxOfficeResult)
                        }
                    }
                }
        }
    }

}

sealed interface MainUiState {
    data object Loading: MainUiState
    data class Error(val errorMsg: String): MainUiState
    data class MovieWeekly(val moviesWeekly: MoviesWeekly): MainUiState
}