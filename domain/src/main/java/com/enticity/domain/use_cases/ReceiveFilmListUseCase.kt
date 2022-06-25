package com.enticity.domain.use_cases

import com.enticity.domain.model.FilmModel
import com.enticity.domain.repository.IFilmRepository

class ReceiveFilmListUseCase(
    private val filmRepository: IFilmRepository
) {
    suspend fun receive(offset: Int, key: String): List<FilmModel> {
        return filmRepository.receiveFilmList(offset, key)
    }
}