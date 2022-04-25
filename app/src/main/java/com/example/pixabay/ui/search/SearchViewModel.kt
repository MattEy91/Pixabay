package com.example.pixabay.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabay.core.GetResult
import com.example.pixabay.domain.model.Hit
import com.example.pixabay.domain.usecase.GetLocalHitsUC
import com.example.pixabay.domain.usecase.SearchHitsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHitUc: SearchHitsUC,
    private val getLocalHitsUC: GetLocalHitsUC
) : ViewModel() {

    private val _searchHits = MutableLiveData<List<Hit>>()
    val searchHits: LiveData<List<Hit>> = _searchHits

    private val _uiState = MutableLiveData(UiState.IDLE)
    val uiState: LiveData<UiState> = _uiState

    var lastQuery: String? = null

    fun searchImagesAsync(query: String, isOnline: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            lastQuery = query
            _uiState.postValue(UiState.LOADING)
            when (val response = if(isOnline) searchHitUc(query) else getLocalHitsUC()) {
                is GetResult.Success -> {
                    response.data.let { _searchHits.postValue(it) }
                    _uiState.postValue(UiState.IDLE)
                }
                is GetResult.Failure -> _uiState.postValue(UiState.SHOW_ERROR)
            }
        }
    }

    enum class UiState {
        LOADING, SHOW_ERROR, IDLE
    }
}