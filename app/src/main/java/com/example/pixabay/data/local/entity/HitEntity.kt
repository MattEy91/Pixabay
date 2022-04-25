package com.example.pixabay.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pixabay.domain.model.Hit

@Entity
data class HitEntity(
    @PrimaryKey val id: Long,
    val previewURL: String,
    val tags: String,
    val user: String,
    val largeImageURL: String,
    val likes: Int,
    val comments: Int,
    val downloads: Int
) {
    fun toHit(): Hit {
        return Hit(
            id = id,
            previewURL = previewURL,
            tags = tags,
            user = user,
            largeImageURL = largeImageURL,
            likes = likes,
            downloads = downloads,
            comments = comments
        )
    }
}
