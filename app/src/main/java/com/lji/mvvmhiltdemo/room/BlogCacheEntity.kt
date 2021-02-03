package com.lji.mvvmhiltdemo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogCacheEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,
    var body: String,
    var category: String,
    var image: String
)