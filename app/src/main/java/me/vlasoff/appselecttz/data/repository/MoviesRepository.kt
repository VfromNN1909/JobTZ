package me.vlasoff.appselecttz.data.repository

import me.vlasoff.appselecttz.data.network.apiservice.MoviesApiService
import me.vlasoff.appselecttz.data.network.apiservice.ResultWrapper
import me.vlasoff.appselecttz.data.network.apiservice.safeApiCall
import me.vlasoff.appselecttz.domain.model.MoviesResponse
import me.vlasoff.appselecttz.domain.repository.IRepository
import me.vlasoff.appselecttz.utils.API_KEY
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiService: MoviesApiService
) : IRepository.IMoviesRepository {

    override suspend fun getMovies(): ResultWrapper<MoviesResponse> =
        safeApiCall { apiService.getMovies(API_KEY) }
}