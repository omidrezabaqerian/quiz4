package com.omidrezabagherian.userapplication.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.omidrezabagherian.userapplication.data.network.NetworkManager
import com.omidrezabagherian.userapplication.model.CreateUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCreateViewModel : ViewModel() {
    private val _user = MediatorLiveData<String>()
    val user: LiveData<String> = _user

    fun registerUser(createUser: CreateUser) {
        NetworkManager.userService.createUser(createUser).enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _user.postValue(response.body())
                    Log.i("userResponse", response.body().toString())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _user.postValue(t.message)
                    Log.i("userThrowable", t.message.toString())
                }

            }
        )
    }

}