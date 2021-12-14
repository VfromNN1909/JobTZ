package me.vlasoff.appselecttz.data.network.apiservice

import me.vlasoff.appselecttz.domain.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("reviews/all.json")
    suspend fun getMovies(): MoviesResponse

}