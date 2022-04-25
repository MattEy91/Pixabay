package com.example.pixabay.data.local

import androidx.room.*
import com.example.pixabay.data.local.entity.HitEntity

@Dao
interface HitDao {

    @Query("SELECT * FROM hitentity")
    fun getHits(): List<HitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHits(hits: List<HitEntity>)

    @Delete
    fun deleteHits(hits: List<HitEntity>)
}