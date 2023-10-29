package com.philexliveprojects.spillatte.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.philexliveprojects.spillatte.api.UnsplashService
import retrofit2.HttpException
import java.io.IOException

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
            val response = service.searchPhotos(query, start, params.loadSize)
            val photos = response.results
            Log.d("UnsplashPagingSource", "LoadResult success")
            LoadResult.Page(
                data = photos,
                prevKey = if (start == STARTING_KEY) null else start - 1,
                nextKey = if (start == response.totalPages) null else start + 1
            )
        } catch(e: IOException) {
            Log.e("UnsplashPagingSource", "IOException: " + e.message.toString())
            LoadResult.Error(e)
        }catch (e: HttpException) {
            Log.e("UnsplashPagingSource", "HttpException: " + e.message.toString())
            LoadResult.Error(e)
        }
    }
}