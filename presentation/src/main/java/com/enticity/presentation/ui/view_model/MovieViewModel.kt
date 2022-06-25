package com.enticity.presentation.ui.view_model

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enticity.domain.use_cases.ReceiveFilmListUseCase
import com.enticity.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val filmUseCases: ReceiveFilmListUseCase): ViewModel() {

    fun receiveFilmList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = filmUseCases.invoke()
            emit(Resource.success(data))
        } catch (e: Exception){
            emit(Resource.error(data =  null, message = e.localizedMessage?: "Unknown error occurred"))
        }
    }
}