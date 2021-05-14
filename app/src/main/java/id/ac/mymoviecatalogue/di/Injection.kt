package id.ac.mymoviecatalogue.di

import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.RemoteDataSource
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig

object Injection {
    fun provideRepository(): FilmRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())

        return FilmRepository.getInstance(remoteDataSource)
    }
}