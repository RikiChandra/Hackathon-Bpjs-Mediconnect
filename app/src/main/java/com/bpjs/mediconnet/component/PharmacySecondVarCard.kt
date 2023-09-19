package com.bpjs.mediconnet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpjs.mediconnet.R

@Composable
fun PharmacySecondVarCard(
    name: String,
    address: String,
    imageUrl: String,
    rating: Double,
    reviewCount: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .size(width = 240.dp, height = 300.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.pharmacy_image),
                modifier = Modifier
                    .size(width = 240.dp, height = 160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp
                )
            )

            Spacer(
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Rating(modifier, rating, reviewCount)

            Spacer(
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Text(
                text = address,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PharmacySecondCardPreview() {
    MyTheme {
        PharmacySecondVarCard(
            name = "Apotek K24",
            address = "ewfewfewfewfew",
            imageUrl = "https://cdn1-production-images-kly.akamaized.net/WRt03bt8AMbzN6bq9Kh8UZ7QtEc=/1200x675/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/656087/original/kimiafarma-140110b.jpg",
            rating = 4.7,
            reviewCount = 7.932,
        )
    }
}