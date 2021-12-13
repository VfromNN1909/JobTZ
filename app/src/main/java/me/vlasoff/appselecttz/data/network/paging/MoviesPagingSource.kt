package me.vlasoff.appselecttz.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.vlasoff.appselecttz.data.network.apiservice.ResultWrapper
import me.vlasoff.appselecttz.data.repository.MoviesRepository
import me.vlasoff.appselecttz.domain.model.MoviesResponse
import me.vlasoff.appselecttz.domain.model.Result
import me.vlasoff.appselecttz.utils.NETWORK_PAGE_SIZE
import me.vlasoff.appselecttz.utils.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MoviesPagingSource @Inject constructor(
    private val repository: MoviesRepository
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = repository.getMovies().let { response ->
                when (response) {
                    is ResultWrapper.Success -> {
                        response.value.results
                    }
                    else -> null
                }
            }
            val nextKey = if (response == null || response.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response!!,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}