package com.shabs.pagepoc

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shabs.pagepoc.models.Data

class PostDataSource(private val apiService: APIService) :
    PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListData(
                currentLoadingPageKey, "BatMan", "132c797b"
            )

            val data = response.body()?.search ?: emptyList()

            val responseData = mutableListOf<Data>()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(data, prevKey, currentLoadingPageKey.plus(1))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        TODO("Not yet implemented")
    }
}