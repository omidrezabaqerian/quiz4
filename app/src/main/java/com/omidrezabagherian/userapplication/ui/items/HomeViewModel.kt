package com.omidrezabagherian.userapplication.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidrezabagherian.userapplication.data.Resource
import com.omidrezabagherian.userapplication.data.UserRepository
import com.omidrezabagherian.userapplication.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _userList = MutableStateFlow<Resource<List<User>>>(Resource.Loading())
    val userList: StateFlow<Resource<List<User>>> = _userList

    fun getUsers() = viewModelScope.launch {
        userRepository.getUsers().collect {
            _userList.emit(it)
        }
    }

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

}