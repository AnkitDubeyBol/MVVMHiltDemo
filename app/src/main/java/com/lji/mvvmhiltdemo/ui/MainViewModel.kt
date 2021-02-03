package com.lji.mvvmhiltdemo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lji.mvvmhiltdemo.model.Blog
import com.lji.mvvmhiltdemo.repository.BlogRepository
import com.lji.mvvmhiltdemo.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Blog>>> = _dataState

    init {
        setStateEvent(MainStateEvent.GetBlogEvents)
    }

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogEvents -> {
                    blogRepository.getBlog()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()
    object None : MainStateEvent()
}