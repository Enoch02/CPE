package com.cpe.ui.screens.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpe.data.repository.FirebaseRepository
import com.cpe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {
    private val _registrationState = Channel<AuthState>()
    private val _loginState = Channel<AuthState>()

    val registrationState = _registrationState.receiveAsFlow()
    val loginState = _loginState.receiveAsFlow()

    fun isUserLoggedIn() = repository.isUserLoggedIn()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(email, password).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _registrationState.send(AuthState(isError = result.message))
                    }

                    is Resource.Loading -> {
                        _registrationState.send(AuthState(isLoading = true))
                    }

                    is Resource.Success -> {
                        _registrationState.send(AuthState(isSuccess = "Registration Complete"))
                    }
                }
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(email, password).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _registrationState.send(AuthState(isError = result.message))
                    }

                    is Resource.Loading -> {
                        _registrationState.send(AuthState(isLoading = true))
                    }

                    is Resource.Success -> {
                        _registrationState.send(AuthState(isSuccess = "Login Successful"))
                    }
                }
            }
        }
    }
}