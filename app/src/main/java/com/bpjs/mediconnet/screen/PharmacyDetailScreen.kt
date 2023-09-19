package com.bpjs.mediconnet.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpjs.mediconnet.R
import com.bpjs.mediconnet.component.Rating
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.DataItem
import com.bpjs.mediconnet.viewmodel.PharmacyDetailViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PharmacyDetailScreen(
    navController: NavController,
    pharmacyId: String,
    viewModel: PharmacyDetailViewModel = hiltViewModel(),
) {

    Column {
        viewModel.dataPharmacy.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getDetailPharmacy(pharmacyId)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    PharmacyDetailContent(pharmacy = uiState.data, navController = navController)
                }
                is UiState.Error -> {
                    Text(text = uiState.errorMessage)
                }
            }
        }
    }
}

@Composable
fun PharmacyDetailContent(
    navController: NavController,
    pharmacy: DataItem
) {

    LazyColumn{
        item{
            Box(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pharmacy.foto)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Button(
                            onClick = {
                                navController.navigateUp()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                                Text(text = "Back")
                            }
                        }

                    }
                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        text = pharmacy.nama,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        Rating(rating = pharmacy.rating.toString().toDoubleOrNull() ?: 0.0,
                            reviewCount = pharmacy.review.toDouble(),
                            modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
        item{
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp, 16.dp),
                            tint = Color.Red
                        )
                        Text(
                            text = pharmacy.alamat,
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item{
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )  {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
        item{
            Spacer(modifier = Modifier.height(36.dp))
            OpenMapButton(pharmacy = pharmacy)
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}

@Composable
private fun OpenMapButton(pharmacy: DataItem) {
    val context = LocalContext.current
    Button(
        onClick = {
            openMap(context = context, pharmacy = pharmacy)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = colorResource(id = R.color.blue),
            contentColor = Color.White
        )
    ) {
        Text("Go To Destination")
    }
}

fun openMap(context: Context, pharmacy: DataItem) {
    val uri = Uri.parse("geo:$pharmacy.lat,$pharmacy.lon?q=${pharmacy.alamat}")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}






