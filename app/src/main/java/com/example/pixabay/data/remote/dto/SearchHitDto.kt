package com.example.pixabay.data.remote.dto

import com.example.pixabay.domain.model.SearchHit

data class SearchHitDto(
    val hits: List<HitDto>,
    val total: Int,
    val totalHits: Int
) {
    fun toSearchHit(): SearchHit {
        return SearchHit(hits = hits.map { it.toHit() })
    }
}