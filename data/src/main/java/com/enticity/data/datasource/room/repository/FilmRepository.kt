package com.enticity.data.datasource.room.repository

import com.enticity.data.datasource.room.MovieApi
import com.enticity.domain.model.FilmModel
import com.enticity.domain.repository.IFilmRepository

class FilmRepository(val movieApi: MovieApi): IFilmRepository {
    override suspend fun receiveFilmList(offset: Int, key: String): List<FilmModel> =
        movieApi.getMovie(offset, key).results.map {
            FilmModel(
                previewUri = it.multimedia.src,
                tittle = it.display_title,
                description = it.summary_short
            )
        }
}