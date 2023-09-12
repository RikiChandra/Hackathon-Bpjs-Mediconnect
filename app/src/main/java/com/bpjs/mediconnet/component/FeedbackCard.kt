package com.bpjs.mediconnet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackCard(name: String, imageUrl: String, modifier: Modifier = Modifier) {
    Card(
        modifier
            .size(
                width = 200.dp, height = 200.dp
            )

    ) {
        Box(
            Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Blue,
                        Color.Red
                    )
                )
            )
        ) {
            Text(text = "teskocak")
        }
    }
}

@Preview
@Composable
fun FeedbackCardPreview() {
    FeedbackCard(name = "Tes bang", imageUrl = "")
}