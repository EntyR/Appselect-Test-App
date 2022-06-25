package com.enticity.domain.repository

import com.enticity.domain.model.FilmModel

interface IFilmRepository {
    suspend fun receiveFilmList(offset: Int, key: String): List<FilmModel>
}