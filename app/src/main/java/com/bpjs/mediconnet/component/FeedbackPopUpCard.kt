package com.bpjs.mediconnet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material.icons.outlined.SentimentNeutral
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.material.icons.outlined.SentimentVeryDissatisfied
import androidx.compose.material.icons.outlined.SentimentVerySatisfied
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bpjs.mediconnet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackPopUpCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(width = 300.dp, height = 400.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val feedbackTitle = AnnotatedString.Builder().apply {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append(stringResource(R.string.feedback_popup_title_1))
                }
                withStyle(style = SpanStyle(color = Color(0xFF86C8BC))) {
                    append(stringResource(R.string.feedback_popup_title_2))
                }
            }.toAnnotatedString()

            Text(
                text = feedbackTitle,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.feedback_popup_pharmacy_and_medicine),
                style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    imageVector = Icons.Outlined.SentimentVeryDissatisfied,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Outlined.SentimentDissatisfied,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Outlined.SentimentNeutral,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Outlined.SentimentSatisfied,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Outlined.SentimentVerySatisfied,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }

            OutlinedTextField(
                value = "Isi pendapat anda", onValueChange = {}, modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(10.dp)
            )


        }
        Text(
            text = stringResource(R.string.feedback_popup_note),
            modifier = Modifier.padding(top = 2.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Preview
@Composable
fun FeedbackPopUpCardPreview() {
    FeedbackPopUpCard()
}