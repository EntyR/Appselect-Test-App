package com.enticity.domain.repository

import com.enticity.domain.model.FilmModel

interface IFilmRepository {
    fun receiveFilmList(): List<FilmModel>
}