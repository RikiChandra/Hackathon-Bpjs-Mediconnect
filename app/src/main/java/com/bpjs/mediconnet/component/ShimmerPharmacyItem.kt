package com.bpjs.mediconnet.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPharmacyItem(
    modifier: Modifier = Modifier,
) {

    val colors = listOf(Color.LightGray, Color.White)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Surface(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = Color.Gray
        ) {}

        Spacer(modifier = Modifier.width(16.dp))

        Column {

            for(i in 1..3) {

                Surface(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    color = colors.random()
                ) {}

                Spacer(modifier = Modifier.height(8.dp))

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun ShimmerPharmacyItemPreview() {
    ShimmerPharmacyItem()
}