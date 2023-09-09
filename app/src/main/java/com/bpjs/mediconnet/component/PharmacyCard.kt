package com.bpjs.mediconnet.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpjs.mediconnet.R


@Composable
fun PharmacyCard(
    name: String,
    address: String,
    imageUrl: String
) {

    Card (
        modifier = Modifier
            .padding(16.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.pharmacy_image),
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 16.sp
                        )
                    )

                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(R.string.favorite),
                        modifier = Modifier.size(24.dp),
                        tint = Color.Red
                    )

                }

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = address,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )

                Rating(4.7f, 7.932f)

            }

        }

    }

}


@Composable
fun Rating(
    rating: Float,
    reviewCount: Float
) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = stringResource(R.string.star),
            modifier = Modifier.size(14.dp),
            tint = Color(0xFFE8C547)
        )

        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = stringResource(R.string.reviews, reviewCount),
            style = MaterialTheme.typography.bodySmall
        )

    }

}

@Preview(showBackground = true)
@Composable
fun PharmacyCardPreview() {
    MyTheme {
        PharmacyCard(
            name = "Apotek K24",
            address = "Jl. Raya Bogor, Jakarta",
            imageUrl = "https://cdn1-production-images-kly.akamaized.net/WRt03bt8AMbzN6bq9Kh8UZ7QtEc=/1200x675/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/656087/original/kimiafarma-140110b.jpg"
        )
    }

}

@Composable
fun MyTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}
