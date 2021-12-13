package me.vlasoff.appselecttz.domain.usecases

import me.vlasoff.appselecttz.data.repository.MoviesRepository
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend fun execute() = repository.getMovies()
}