package com.enticity.domain.use_cases

import com.enticity.domain.model.FilmModel
import com.enticity.domain.repository.IFilmRepository

class ReceiveFilmListUseCase(
    private val filmRepository: IFilmRepository
) {
    operator fun invoke(): List<FilmModel> {
        return filmRepository.receiveFilmList()
    }
}