package com.bpjs.mediconnet.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpjs.mediconnet.component.FeedbackCard
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.feedbackModel.Feedback
import com.bpjs.mediconnet.viewmodel.FeedbackViewModel

@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedbackViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Feedback",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            fontSize = 24.sp
        )

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getFeedback()
                }

                is UiState.Success -> {
                    FeedbackContent(
                        feedback = uiState.data,
                        onClick = onClick
                    )
                }

                is UiState.Error -> {
                    Text(text = uiState.errorMessage)
                }
            }
        }
    }
}

@Composable
fun FeedbackContent(
    feedback: List<Feedback>,
    onClick: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(feedback) {
            FeedbackCard(
                name = it.name,
                imagePainter = it.imageSource,
                onClick = onClick
            )
        }
    }
}