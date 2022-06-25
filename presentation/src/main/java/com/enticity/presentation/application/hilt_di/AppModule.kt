package com.enticity.presentation.application.hilt_di

import com.enticity.data.datasource.room.MovieApi
import com.enticity.data.datasource.room.repository.FilmRepository
import com.enticity.domain.repository.IFilmRepository
import com.enticity.domain.use_cases.ReceiveFilmListUseCase
import com.enticity.presentation.application.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideFilmUseCase(filmRepository: IFilmRepository) =
        ReceiveFilmListUseCase(filmRepository)

    @Provides
    fun provideRepository(api: MovieApi): IFilmRepository =
        FilmRepository(api)

}