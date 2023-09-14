package com.bpjs.mediconnet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bpjs.mediconnet.R

@Composable
fun FeedbackCard(name: String, imagePainter: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(
                width = 200.dp, height = 200.dp
            )
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF1EA78F), Color(0xFF8DCBC0))
                ),
                shape = RoundedCornerShape(12.dp)
            )

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imagePainter),
                contentDescription = stringResource(R.string.dummy_content_desc)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name, style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 16.sp
                ), color = Color.White, textAlign = TextAlign.Center
            )
        }
    }
}


@Preview
@Composable
fun FeedbackCardPreview() {
    FeedbackCard(name = "Keluhan dan Pelayanan", imagePainter = R.drawable.ic_launcher_background)
}