package com.bpjs.mediconnet.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpjs.mediconnet.component.CategoryChips
import com.bpjs.mediconnet.component.MedicineCard
import com.bpjs.mediconnet.component.PharmacyCard
import com.bpjs.mediconnet.elements.HeaderScreen
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.DataItem
import com.bpjs.mediconnet.model.Medicine
import com.bpjs.mediconnet.viewmodel.MedicineViewModel
import com.bpjs.mediconnet.viewmodel.PharmacyViewModel

@Composable
fun HomeScreen(
    medicineViewModel: MedicineViewModel = hiltViewModel(),
    pharmacyViewModel: PharmacyViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToPharmacy: (String) -> Unit,
    navigateToMedicine: (String) -> Unit
) {
    val sicknessChips = listOf(
        "Demam Tinggi",
        "Dengus",
        "Mual",
        "Batuk",
    )

    LazyColumn {
        item {
            HeaderScreen(
                query = "",
                onQueryChange = {},
                onClickChat = {},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            Text(
                text = "Gejala anda",
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 24.sp
                )
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                items(sicknessChips) {
                    CategoryChips(labelName = it) {
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Text(
                text = "Jenis Obat",
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 24.sp
                )
            )

            medicineViewModel.dataMedicine.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        medicineViewModel.getMedicine()
                    }

                    is UiState.Success -> {
                        MedicineHomeContent(
                            dataMedicine = uiState.data,
                            navigateToDetail = navigateToMedicine
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

            Text(
                text = "Apotek Terbaik",
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp
                )
            )

            pharmacyViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        pharmacyViewModel.getPharmacies()
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is UiState.Success -> {
                        PharmacyHomeContent(
                            pharmacies = uiState.data,
                            navigateToDetail = navigateToPharmacy
                        )

                    }

                    is UiState.Error -> {
                        Text(text = uiState.errorMessage)
                    }
                }
            }
        }
    }
}

@Composable
fun PharmacyHomeContent(
    pharmacies: List<DataItem> = emptyList(),
    navigateToDetail: (String) -> Unit
) {
    LazyRow {
        items(pharmacies) { pharmacy ->
            PharmacyCard(
                name = pharmacy.nama,
                address = pharmacy.alamat,
                imageUrl = pharmacy.foto,
                rating = pharmacy.rating.toString().toDoubleOrNull() ?: 0.0,
                reviewCount = pharmacy.review.toDouble(),
                modifier = Modifier.clickable {
                    navigateToDetail(pharmacy.id)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MedicineHomeContent(
    dataMedicine: List<Medicine>,
    navigateToDetail: (String) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(dataMedicine, key = { it.id }) { medicine ->
            MedicineCard(
                name = medicine.nama,
                description = medicine.deskripsi,
                imageUrl = medicine.foto,
                modifier = Modifier.clickable {
                    navigateToDetail(medicine.id)
                })
        }
    }
}
