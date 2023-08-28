package com.philexliveprojects.spillatte.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.philexliveprojects.spillatte.api.UnsplashService

private const val STARTING_KEY = 1

class UnsplashPagingSource(
    private val service: UnsplashService,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val start = params.key ?: STARTING_KEY

        return try {
            val responce = service.searchPhotos(query, start, params.loadSize)
            val photos = responce.results
            LoadResult.Page(
                data = photos,
                prevKey = if (start == STARTING_KEY) null else start - 1,
                nextKey = if (start == responce.totalPages) null else start + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}