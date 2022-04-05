package com.omidrezabagherian.userapplication.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.omidrezabagherian.userapplication.data.network.NetworkManager
import com.omidrezabagherian.userapplication.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel : ViewModel() {
    private val _searchResponse = MediatorLiveData<List<User>>()
    val searchResponse: LiveData<List<User>> = _searchResponse
    private val _searchThrowable = MediatorLiveData<String>()
    val searchThrowable: LiveData<String> = _searchThrowable

    fun showUserList() {
        NetworkManager.userService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _searchResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _searchThrowable.postValue(t.message)
            }
        })
    }

}