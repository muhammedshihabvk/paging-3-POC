package com.shabs.pagepoc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData

class MainViewModel(private val apiService: APIService) : ViewModel(){

    val listData = Pager(PagingConfig(pageSize = 6)){
        PostDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}