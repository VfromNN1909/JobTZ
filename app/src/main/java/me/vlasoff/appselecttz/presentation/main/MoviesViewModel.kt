package me.vlasoff.appselecttz.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import me.vlasoff.appselecttz.domain.model.Result
import me.vlasoff.appselecttz.domain.usecases.GetAllMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor (
    private val useCase: GetAllMoviesUseCase
) : ViewModel()  {

    fun getMovies() =
        useCase.execute()
            .map { pagingData ->
                pagingData.map { movieFromNetwork ->
                    Result.mapToUi(movieFromNetwork)
                }
            }
            .cachedIn(viewModelScope)

}