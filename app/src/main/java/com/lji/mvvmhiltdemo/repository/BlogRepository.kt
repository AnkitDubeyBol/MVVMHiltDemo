package com.lji.mvvmhiltdemo.repository

import com.lji.mvvmhiltdemo.model.Blog
import com.lji.mvvmhiltdemo.networking.BlogAPIClient
import com.lji.mvvmhiltdemo.networking.NetworkMapper
import com.lji.mvvmhiltdemo.room.BlogDao
import com.lji.mvvmhiltdemo.room.CacheMapper
import com.lji.mvvmhiltdemo.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BlogRepository constructor(
    private val apiClient: BlogAPIClient,
    private val blogDao: BlogDao,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper
){
    suspend fun getBlog() : Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = apiClient.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            blogDao.insertAll(cacheMapper.mapToEntityList(blogs))
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (ex : Exception){
            emit(DataState.Error(ex))
        }
    }
}