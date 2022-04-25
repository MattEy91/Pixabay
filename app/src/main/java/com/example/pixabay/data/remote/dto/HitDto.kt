package com.example.pixabay.data.remote.dto

import com.example.pixabay.domain.model.Hit
import com.google.gson.annotations.SerializedName

data class HitDto(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Long,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    @SerializedName("user_id")
    val userId: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
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