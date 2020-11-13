package com.ydh.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response


class UserViewModel : BaseViewModel() {
//    private val _data: MutableLiveData<List<UserModel>> by lazy {
//        MutableLiveData<List<UserModel>>()
//    }
//
//    val data : LiveData<List<UserModel>>
//        get() = _data
//
//    private val _response = MutableLiveData<String>()
//    val response : LiveData<String>
//        get() = _response
//
//
//    fun setAllUser(){
//        NetUtilUser.apiService.getAllUser(1, 2).enqueue(object :
//                retrofit2.Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                val userResponse = response.body()
//                _data.postValue(userResponse?.result)
//                Log.d("TAG", "Response = $_data");
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Log.d("TAG", "Response = $t");
//            }
//
//        }
//
//        )
//
//    }

    private val networkService = UserService.getService()
    var newsList: LiveData<PagedList<UserModel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: UserDataSourceFactory

    init {
        newsDataSourceFactory = UserDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
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