package com.enticity.presentation.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enticity.domain.use_cases.ReceiveFilmListUseCase
import com.enticity.presentation.application.API_KEY
import com.enticity.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val filmUseCases: ReceiveFilmListUseCase): ViewModel() {

    fun receiveFilmList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = filmUseCases.receive(0, API_KEY)
            emit(Resource.success(data))
        } catch (e: Exception){
            emit(Resource.error(data =  null, message = e.localizedMessage?: "Unknown error occurred"))
        }
    }
}