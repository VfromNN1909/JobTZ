package me.vlasoff.appselecttz.domain.usecases

import me.vlasoff.appselecttz.data.network.paging.MoviesRemoteDataSource
import me.vlasoff.appselecttz.data.repository.MoviesRepository
import javax.inject.Inject

// мне немножко лень исправлять юзкейс сейчас, но я понял, что он особо не нужен
class GetAllMoviesUseCase @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {
    fun execute() = moviesRemoteDataSource.getMovies()
}