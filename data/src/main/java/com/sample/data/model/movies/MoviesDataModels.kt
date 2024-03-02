package com.sample.data.model.movies

data class MoviesApiModels(
    val boxOfficeResult: MoviesWeekly
)

data class MoviesWeekly(
    val boxofficeType: String,
    val showRange: String,
    val weeklyBoxOfficeList: List<Movie>
)

data class Movie(
    val rank: String,
    val rankOldAndNew: String,
    val movieCd: String,
    val movieNm: String,
    val salesAcc: String,
    val audiAcc: String
)