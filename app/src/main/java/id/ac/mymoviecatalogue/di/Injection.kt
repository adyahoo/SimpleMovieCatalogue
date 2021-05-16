package id.ac.mymoviecatalogue.di

import android.content.Context
import id.ac.mymoviecatalogue.data.FilmRepository
import id.ac.mymoviecatalogue.data.source.local.LocalDataSource
import id.ac.mymoviecatalogue.data.source.local.room.FilmDatabase
import id.ac.mymoviecatalogue.data.source.remote.RemoteDataSource
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FilmRepository {
        val db = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())
        val localDataSource = LocalDataSource.getInstance(db.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}