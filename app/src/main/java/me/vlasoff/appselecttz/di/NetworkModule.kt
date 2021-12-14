package me.vlasoff.appselecttz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vlasoff.appselecttz.data.network.apiservice.MoviesApiService
import me.vlasoff.appselecttz.data.network.apiservice.MoviesInterceptor
import me.vlasoff.appselecttz.data.network.paging.MoviesRemoteDataSource
import me.vlasoff.appselecttz.data.repository.MoviesRepository
import me.vlasoff.appselecttz.domain.usecases.GetAllMoviesUseCase
import me.vlasoff.appselecttz.presentation.main.MoviesViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://api.nytimes.com/svc/movies/v2/"

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(MoviesInterceptor())
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String, client: OkHttpClient) =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    @Provides
    fun providesMoviesApiService(retrofit: Retrofit) =
        retrofit.create(MoviesApiService::class.java)

    @Provides
    fun providesMoviesRepository(apiService: MoviesApiService) = MoviesRepository(apiService)

    @Provides
    fun providesMovesRemoteDataSource(repository: MoviesRepository) =
        MoviesRemoteDataSource(repository)

    @Provides
    fun providesGetMoviesUseCase(moviesRemoteDataSource: MoviesRemoteDataSource) =
        GetAllMoviesUseCase(moviesRemoteDataSource)

    @Provides
    fun provideMoviesViewModel(useCase: GetAllMoviesUseCase) =
        MoviesViewModel(useCase)
}