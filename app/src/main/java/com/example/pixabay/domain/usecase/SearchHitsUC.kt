package com.example.pixabay.domain.usecase

import com.example.pixabay.domain.repository.HitRepository

class SearchHitsUC(
    private val repository: HitRepository
) {
    suspend operator fun invoke(query: String) = repository.getAllHitsRemote(query)
}