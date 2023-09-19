package com.bpjs.mediconnet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bpjs.mediconnet.R
import com.bpjs.mediconnet.ui.theme.MediconnetTheme
import com.bpjs.mediconnet.viewmodel.ChatGPTViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBotScreen(
    navController: NavController,
    viewModel: ChatGPTViewModel = hiltViewModel()
) {
    viewModel.isLoading.collectAsState().value.let { state ->
        if (state) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                Card {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(PaddingValues(bottom = 8.dp)),
                title = {
                    Text(
                        text = "Chat",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable { navController.navigateUp() }
                    )
                },
            )
        },
        modifier = Modifier.padding(8.dp)
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(8.dp, MaterialTheme.shapes.medium),
                colors = CardDefaults.cardColors(Color.White),
            ) {
                Text("MediBot", modifier = Modifier.padding(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = CardDefaults.cardColors(Color.Green),
                    ) {

                    }
                    Text("Always Active")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.dataMessages.reversed()) { message ->

                    if (message.isUser) {
                        MessageBubbleUser(message.content)
                    } else {
                        MessageBubbleBot(message.content)
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                var inputText by remember { mutableStateOf("") }
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = Color.Black
                    ),
                    label = { Text("Type a message") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        viewModel.sendMessage(inputText)
                        inputText = ""
                    })
                )

                IconButton(
                    onClick = {
                        viewModel.sendMessage(inputText)
                        inputText = ""
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send message")
                }
            }
        }
    }
}

@Composable
fun MessageBubbleBot(text: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.shadow(8.dp, MaterialTheme.shapes.extraLarge)
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(18.dp),
                painter = painterResource(id = R.drawable.robot),
                contentDescription = null
            )
        }

        Card(
            shape = MaterialTheme.shapes.medium.copy(
                topStart = CornerSize(0.dp)
            ),
            colors = CardDefaults.cardColors(Color(0xFF86C8BC)),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Composable
fun MessageBubbleUser(text: String) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            shape = MaterialTheme.shapes.medium.copy(
                topEnd = CornerSize(0.dp)
            ),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.shadow(
                4.dp, MaterialTheme.shapes.medium.copy(
                    topEnd = CornerSize(0.dp)
                )
            )
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color(0xFF86C8BC),
                textAlign = TextAlign.End,
            )
        }

        Divider(modifier = Modifier.width(8.dp), color = Color.Transparent)

        Card(
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.shadow(8.dp, MaterialTheme.shapes.extraLarge)
        ) {
            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .size(18.dp),
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        }
    }
}