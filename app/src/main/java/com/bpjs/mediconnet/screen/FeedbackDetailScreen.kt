package com.bpjs.mediconnet.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bpjs.mediconnet.R
import com.bpjs.mediconnet.component.FeedbackPopUpCard
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.navigation.BottomNavScreen
import com.bpjs.mediconnet.viewmodel.FeedbackDetailViewModel

@Composable
fun FeedbackDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedbackDetailViewModel = hiltViewModel(),
    feedbackId: Long,
    navController: NavController
) {
    var ratingValue by remember {
        mutableIntStateOf(0)
    }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFeedbackScreenById(feedbackId)
            }

            is UiState.Success -> {
                val data = uiState.data
                FeedbackDetailContent(
                    imageHeader = data.imageHeader,
                    question = data.question,
                    ratingValue = ratingValue,
                    navController = navController
                )
            }

            is UiState.Error -> {
                Text(text = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun FeedbackDetailContent(
    question: String,
    imageHeader: Int,
    ratingValue: Int,
    viewModel: FeedbackDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val contextHelper = LocalContext.current

    var review by rememberSaveable {
        mutableStateOf("")
    }

    var currentRating by remember { mutableIntStateOf(ratingValue) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.weight(1f), // Take up available vertical space
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(imageHeader),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            FeedbackPopUpCard(
                value = review,
                onValueChange = { review = it },
                question = question,
                onRatingValueChange = { newRating ->
                    currentRating = newRating
                }
            )
        }

        Button(
            onClick = {
                viewModel.postFeedback(currentRating, review)
                navController.popBackStack(BottomNavScreen.Feedback.route, inclusive = true)
                Toast.makeText(contextHelper, "Feedback anda telah kami terima", Toast.LENGTH_SHORT)
                    .show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.feedback_button_title))
        }
    }
}