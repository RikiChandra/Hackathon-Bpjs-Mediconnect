package com.bpjs.mediconnet.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpjs.mediconnet.navigation.BottomNavScreen
import com.bpjs.mediconnet.navigation.Screen
import com.bpjs.mediconnet.repository.DataStoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashScreenViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel()  {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = BottomNavScreen.Home.route
                } else {
                    _startDestination.value = Screen.OnBoarding.route
                }
            }
            _isLoading.value = false
        }
    }
}