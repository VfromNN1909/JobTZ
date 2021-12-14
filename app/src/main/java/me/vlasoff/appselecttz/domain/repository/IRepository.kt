package me.vlasoff.appselecttz.domain.repository

import me.vlasoff.appselecttz.data.network.apiservice.ResultWrapper
import me.vlasoff.appselecttz.domain.model.MoviesResponse
import me.vlasoff.appselecttz.domain.model.Result
import retrofit2.Response

interface IRepository {
    interface IMoviesRepository {
        suspend fun getMovies(): ResultWrapper<MoviesResponse>
    }
}