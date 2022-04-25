package com.example.pixabay.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pixabay.domain.model.Hit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _hit = MutableLiveData<Hit>()
    val hit: LiveData<Hit> = _hit

    fun setSelectedHit(selectedHit: Hit) {
        _hit.value = selectedHit
    }
}