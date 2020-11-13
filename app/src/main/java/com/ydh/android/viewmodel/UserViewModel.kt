package com.ydh.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ydh.android.common.State
import com.ydh.android.data.UserDataSource
import com.ydh.android.data.UserDataSourceFactory
import com.ydh.android.data.UserService
import com.ydh.android.model.UserModel
import io.reactivex.disposables.CompositeDisposable


class UserViewModel : BaseViewModel() {

    private val networkService = UserService.getService()
    var newsList: LiveData<PagedList<UserModel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: UserDataSourceFactory

    init {
        newsDataSourceFactory = UserDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder<Int, UserModel>(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<UserDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, UserDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}