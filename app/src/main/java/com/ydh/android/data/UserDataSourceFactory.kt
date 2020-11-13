package com.ydh.android.data

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import androidx.paging.DataSource
import com.ydh.android.model.UserModel


class UserDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: UserService
)
    : DataSource.Factory<Int, UserModel>() {

     val newsDataSourceLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, UserModel> {
        val newsDataSource = UserDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}