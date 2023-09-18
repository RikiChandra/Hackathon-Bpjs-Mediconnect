package com.bpjs.mediconnet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Sick
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpjs.mediconnet.component.CategoryChips
import com.bpjs.mediconnet.elements.HeaderScreen
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.SicknessChip
import com.bpjs.mediconnet.viewmodel.MedicineViewModel
import com.bpjs.mediconnet.viewmodel.PharmacyViewModel

@Composable
fun HomeScreen(
    medicineViewModel: MedicineViewModel = hiltViewModel(),
    pharmacyViewModel: PharmacyViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Column(
    ) {
        HeaderScreen(
            query = "",
            onQueryChange = {},
            onClickChat = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        Text(text = "Gejala anda")
        LazyRow {
            items(6) {
                CategoryChips(labelName = "Chips euy", icon = Icons.Default.Home) {
                }
            }
        }

        medicineViewModel.dataMedicine.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    medicineViewModel.getMedicine()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    MedicineContent(
                        dataMedicine = uiState.data,
                        navigateToDetail = { }
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
                    PharmacyContent(pharmacies = uiState.data, navigateToDetail = {})

                }

                is UiState.Error -> {
                    Text(text = uiState.errorMessage)
                }

                else -> {}
            }
        }
    }
}