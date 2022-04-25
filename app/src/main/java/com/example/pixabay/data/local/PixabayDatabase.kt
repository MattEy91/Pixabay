package com.example.pixabay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pixabay.data.local.entity.HitEntity

@Database(
    entities = [HitEntity::class],
    version = 1
)
abstract class PixabayDatabase : RoomDatabase() {

    abstract val dao: HitDao

}