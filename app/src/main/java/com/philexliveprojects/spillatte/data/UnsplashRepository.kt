package com.philexliveprojects.spillatte.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.philexliveprojects.spillatte.api.UnsplashService
import kotlinx.coroutines.flow.Flow

class UnsplashRepository(
    private val service: UnsplashService
) {
    fun getPhotos(query: String): Flow<PagingData<UnsplashPhoto>> {
        return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
                pagingSourceFactory = { UnsplashPagingSource(service, query) }
            ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}