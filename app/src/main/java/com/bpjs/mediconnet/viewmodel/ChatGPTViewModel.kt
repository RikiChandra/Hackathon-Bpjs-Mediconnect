package com.bpjs.mediconnet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpjs.mediconnet.api.ChatGPTService
import com.bpjs.mediconnet.model.ChatGPTRequestBody
import com.bpjs.mediconnet.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGPTViewModel @Inject constructor(private val api: ChatGPTService) :
    ViewModel() {

//    private val _uiStateMessages: MutableStateFlow<UiState<List<Message>>> =
//        MutableStateFlow(UiState.Loading)
//    val uiStateMessages: StateFlow<UiState<List<Message>>> get() = _uiStateMessages

    val dataMessages = mutableStateListOf<Message>()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        sendMessage("Kamu adalah bot untuk pelayanan kesehatan masyarakat JKN! Tanyakan ini kepada user: Halo, Saya adalah MediBot yang akan membantu menjawab pertanyaan seputar kesehatan", true)
        dataMessages.remove(dataMessages.first())
    }

    fun sendMessage(text: String, isUser: Boolean = true) {
        dataMessages.add(Message(text, "user"))
        if (isUser) {
            _isLoading.value = true
            viewModelScope.launch {
                val response = api.sendMessage(ChatGPTRequestBody(messages = dataMessages))
                dataMessages.add(response.choices.first().message)
                _isLoading.value = false
            }
        }
    }

//    fun sendMessage(text: String, isUser: Boolean = true) {
//        val newMessage = Message(text, "user")
//        dataMessages.add(newMessage)
//        _uiStateMessages.value = UiState.Success(dataMessages.toList())
//        if (isUser) {
//            viewModelScope.launch {
//                val dataResponse = repository.sendMessage(dataMessages)
//                dataResponse.catch { exception ->
//                    _uiStateMessages.value = UiState.Error(exception.message.toString())
//                }.collect { data ->
//                    dataMessages.add(data.choices.first().message)
//                    _uiStateMessages.value = UiState.Success(dataMessages.toList())
//                }
//            }
//        }
//    }
}