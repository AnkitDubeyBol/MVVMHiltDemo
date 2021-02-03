package com.lji.mvvmhiltdemo.di

import com.lji.mvvmhiltdemo.networking.BlogAPIClient
import com.lji.mvvmhiltdemo.networking.NetworkMapper
import com.lji.mvvmhiltdemo.repository.BlogRepository
import com.lji.mvvmhiltdemo.room.BlogDao
import com.lji.mvvmhiltdemo.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        blogDao: BlogDao,
        apiClient: BlogAPIClient,
        networkMapper: NetworkMapper,
        cacheMapper: CacheMapper
    ): BlogRepository {
        return BlogRepository(apiClient, blogDao, networkMapper, cacheMapper)
    }

}