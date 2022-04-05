package com.omidrezabagherian.userapplication.ui.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.omidrezabagherian.userapplication.data.network.NetworkManager
import okhttp3.MediaType
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadImageViewModel : ViewModel() {

    private val _imageResponse = MediatorLiveData<String>()
    val imageResponse: LiveData<String> = _imageResponse
    private val _imageThrowable = MediatorLiveData<String>()
    val imageThrowable: LiveData<String> = _imageThrowable

    fun uploadImage(id: String, image: ByteArray) {
        val body = MultipartBody.create(MediaType.parse("image/*"), image)
        val request = MultipartBody.Part.createFormData("image", "image.jpg", body)
        NetworkManager.userService.uploadImageUser(id, request).enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                _imageResponse.postValue(response.body().toString())
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                _imageThrowable.postValue(t.message)
            }
        })
    }

}