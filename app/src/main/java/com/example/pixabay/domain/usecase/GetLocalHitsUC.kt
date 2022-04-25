package com.example.pixabay.domain.usecase

import com.example.pixabay.domain.repository.HitRepository

class GetLocalHitsUC(
    private val repository: HitRepository
) {
    suspend operator fun invoke() = repository.getAllHitsLocal()
}