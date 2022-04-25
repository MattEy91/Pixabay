package com.example.pixabay.domain.repository

import com.example.pixabay.core.GetResult
import com.example.pixabay.domain.model.Hit

interface HitRepository {
    suspend fun getAllHitsRemote(query: String): GetResult<List<Hit>>
    suspend fun getAllHitsLocal(): GetResult<List<Hit>>
}