package me.vlasoff.appselecttz.data.network.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.stateIn
import me.vlasoff.appselecttz.data.repository.MoviesRepository
import me.vlasoff.appselecttz.utils.NETWORK_PAGE_SIZE
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val repository: MoviesRepository
) {
    fun getMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            pagingSourceFactory = {
                MoviesPagingSource(repository = repository)
            }
        ).flow
}