package com.bpjs.mediconnet.model

data class ChatGPTResponse(
    val choices: List<MessageResponse>
)

data class MessageResponse(
    val message: Message
)

data class Message(
    val content: String,
    val role: String,
) {
    val isUser: Boolean
        get() = role == "user"
}