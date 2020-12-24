package com.teravision.challenge.ui

import androidx.lifecycle.*
import com.teravision.challenge.domain.entity.UserEntity
import com.teravision.challenge.domain.usecase.GetUsersUseCase
import com.teravision.challenge.domain.usecase.network.HaveConnectivityUseCase
import kotlinx.coroutines.launch

class UsersVM(
    private val getUsers: GetUsersUseCase,
    private val hasConnectivityUseCase: HaveConnectivityUseCase
): ViewModel() {

    private val _users: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val users: LiveData<List<UserEntity>> = _users

    private val _user: MutableLiveData<UserEntity> = MutableLiveData()
    val user: LiveData<UserEntity> = _user

    private val _isErrorThrown = MutableLiveData<Boolean>()
    val isErrorThrown: LiveData<Boolean> = _isErrorThrown

    fun initLoading() {
        viewModelScope.launch {
            try {
                _users.postValue(getUsers.invoke())
                _isErrorThrown.postValue(false)
            } catch (e: Exception) {
                _isErrorThrown.postValue(true)
            }
        }
    }

    fun hasConnectivity(): LiveData<Boolean> {
        return liveData {
            emit(hasConnectivityUseCase())
        }
    }

    fun itemClicked(id: Int) {
        _user.value = users.value!!.find { userId -> id == userId.id }
    }
}