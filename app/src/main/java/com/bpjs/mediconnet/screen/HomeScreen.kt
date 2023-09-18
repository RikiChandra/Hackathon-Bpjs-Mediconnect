package com.bpjs.mediconnet.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.bpjs.mediconnet.elements.HeaderScreen
import com.bpjs.mediconnet.helper.UiState
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

    Column(
    ) {
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
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(sicknessChips) {
                CategoryChips(labelName = it) {
                }
            }
        }

        Text(
            text = "Jenis Obat",
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp)
        )

        medicineViewModel.dataMedicine.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    medicineViewModel.getMedicine()
                }

                is UiState.Success -> {
                    MedicineContent(
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
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp)
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
                    PharmacyContent(
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
