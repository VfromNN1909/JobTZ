package me.vlasoff.appselecttz.domain.usecases

import me.vlasoff.appselecttz.data.network.paging.MoviesRemoteDataSource
import me.vlasoff.appselecttz.data.repository.MoviesRepository
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {
    fun execute() = moviesRemoteDataSource.getMovies()
}