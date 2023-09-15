package com.bpjs.mediconnet.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpjs.mediconnet.component.HeaderWithBackButton
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.Medicine
import com.bpjs.mediconnet.ui.theme.MediconnetTheme
import com.bpjs.mediconnet.viewmodel.MedicineDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(
    medicineId: String,
    navController: NavController,
    viewModel: MedicineDetailViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
//            HeaderWithBackButton(title = "Detail Obat") {
//                navController.navigateUp()
//            }
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(PaddingValues(bottom = 8.dp)),
                title = {
                    Text(
                        text = "Detail Obat",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable { navController.navigateUp() }
                    )
                },
            )
        },
        modifier = Modifier.padding(16.dp)
    ) { innerPadding ->
        viewModel.dataMedicine.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getDetailMedicine(medicineId)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    MedicineDetailContent(
                        data = uiState.data,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                is UiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.errorMessage)
                    }
                }
            }
        }
    }
}

@Composable
fun MedicineDetailContent(
    data: Medicine,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.foto)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds,
            )

        }
        item {
            Card {
                Text(
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 24.sp
                    ), text = data.nama ?: ""
                )
            }
        }
        item {
            MedicineDetailContentCard("Deskripsi", data.deskripsi)
        }
        item {
            MedicineDetailContentCard("Dosis", data.dosis)
        }
        item {
            MedicineDetailContentCard("Efek Samping", data.efek)
        }
    }
}

@Composable
fun MedicineDetailContentCard(
    contentDesc: String,
    contentData: String,
) {
    Card {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = contentDesc, style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp
                )
            )
            Text(
                text = contentData ?: "",
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MedicineDetailScreenPreview() {
    MediconnetTheme {
        MedicineDetailScreen(medicineId = "", navController = rememberNavController())
    }
}