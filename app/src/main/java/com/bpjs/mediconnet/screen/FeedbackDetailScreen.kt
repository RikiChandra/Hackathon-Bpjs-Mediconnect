package com.bpjs.mediconnet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.bpjs.mediconnet.R
import com.bpjs.mediconnet.component.FeedbackPopUpCard

@Composable
fun FeedbackDetailScreen(modifier: Modifier = Modifier) {
    var review by rememberSaveable {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.puzzle_feedback),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        FeedbackPopUpCard(
            value = review,
            onValueChange = { review = it },
            placeholder = stringResource(
                R.string.feedback_thought,
            )
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun FeedbackDetailScreenPreview() {
    FeedbackDetailScreen()
}