package com.enticity.presentation.ui.view_model

import android.util.Log
import androidx.lifecycle.*
import com.enticity.domain.model.FilmModel
import com.enticity.domain.use_cases.ReceiveFilmListUseCase
import com.enticity.presentation.application.API_KEY
import com.enticity.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val filmUseCases: ReceiveFilmListUseCase) :
    ViewModel() {

    private val _page = MutableLiveData<Int>(0)
    val page: LiveData<Int> = _page

    private val _movieList = MutableLiveData<Resource<List<FilmModel>>>()
    val movieList: LiveData<Resource<List<FilmModel>>> = _movieList

    fun receiveFilmList(offset: Int) = viewModelScope.launch {
        _movieList.value = Resource.loading(data = null)
        try {
            val data = filmUseCases.receive(offset, API_KEY)
            _movieList.value = Resource.success(data = data)
        } catch (e: Exception) {
            _movieList.value = Resource.error(message = e.localizedMessage?: "Unknown Error", data = null)
        }
    }

    fun nextPage(){
        _page.value = _page.value!!.toInt() + 1
        Log.e("TAG", "nextPage: ${page.value}", )
    }
    fun previousPage(){
        _page.value = _page.value!!.toInt() - 1
        Log.e("TAG", "nextPage: ${page.value}", )
    }
}