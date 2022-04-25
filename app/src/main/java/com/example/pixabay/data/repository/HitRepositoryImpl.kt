package com.example.pixabay.data.repository

import com.example.pixabay.core.GetResult
import com.example.pixabay.data.local.HitDao
import com.example.pixabay.data.remote.PixabayApi
import com.example.pixabay.domain.model.Hit
import com.example.pixabay.domain.repository.HitRepository
import java.lang.Exception

class HitRepositoryImpl(
    private val api: PixabayApi,
    private val dao: HitDao
) : HitRepository {

    override suspend fun getAllHitsRemote(query: String): GetResult<List<Hit>> {
        return try {
            val remoteSearchHit = api.searchHitsAsync(query = query).await().toSearchHit()
            val hits = remoteSearchHit.hits

            dao.deleteHits(dao.getHits())
            dao.insertHits(hits.map { it.toHitEntity() })

            GetResult.Success(hits)
        } catch (e: Exception) {
            GetResult.Failure()
        }
    }

    override suspend fun getAllHitsLocal(): GetResult<List<Hit>> {
        return try {
            GetResult.Success(dao.getHits().map { it.toHit() })
        } catch (e: Exception) {
            GetResult.Failure()
        }
    }
}