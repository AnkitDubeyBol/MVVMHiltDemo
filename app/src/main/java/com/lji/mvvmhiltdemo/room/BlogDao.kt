package com.lji.mvvmhiltdemo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(blogCacheEntity: List<BlogCacheEntity>)

    @Query("select * from blogs")
    suspend fun get() : List<BlogCacheEntity>
}