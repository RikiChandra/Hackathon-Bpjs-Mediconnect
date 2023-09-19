package com.bpjs.mediconnet.model

data class ChatGPTRequestBody(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
)