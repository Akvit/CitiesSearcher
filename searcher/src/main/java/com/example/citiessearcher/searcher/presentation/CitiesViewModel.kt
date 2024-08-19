package com.example.citiessearcher.searcher.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citiessearcher.searcher.domain.interactors.DownloadCitiesInteractor
import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CitiesViewModel(private val interactor: DownloadCitiesInteractor): ViewModel() {

    private val _citiesLiveData = MutableLiveData<List<CityModel>>()
    val citiesLiveData: LiveData<List<CityModel>> = _citiesLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val parameterForQuery = ParameterForQuery()
    private var searchJob: Job? = null

    private val handler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception.message)
    }

    fun startLoading(query: String? = null) {
        parameterForQuery.query = query.orEmpty()

        searchJob?.cancel()
        _isLoading.postValue(true)
        searchJob = viewModelScope.launch(handler) {

            val response = interactor.getCities(parameterForQuery)

            _citiesLiveData.postValue(response)
            _isLoading.postValue(false)
        }
    }
}