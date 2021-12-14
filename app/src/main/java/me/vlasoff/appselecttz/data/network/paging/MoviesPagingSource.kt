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
        return try {
            val response = repository.getMovies().let { response ->
                when (response) {
                    is ResultWrapper.Success -> {
                        response.value.results
                    }
                    else -> null
                }
            }
            LoadResult.Page(
                // надо было как-то обернуть, наверное, но пусть будет, если упадет сразу будет понятно в чем дело
                data = response!!,
                prevKey = null,
                // вот в этом была проблема
                nextKey = params.key?.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}