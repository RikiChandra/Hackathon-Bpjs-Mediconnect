package com.bpjs.mediconnet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpjs.mediconnet.component.PharmacyCard
import com.bpjs.mediconnet.component.ShimmerPharmacyItem
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.DataItem
import com.bpjs.mediconnet.viewmodel.PharmacyViewModel


@Composable
fun PharmacyScreen(
    viewModel: PharmacyViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                LazyColumn {
                    items(10) {
                        ShimmerPharmacyItem()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                viewModel.getPharmacies()
            }
            is UiState.Success -> {
                PharmacyContent(pharmacies = uiState.data)

            }
            is UiState.Error -> {
                Text(text = uiState.errorMessage)
            }
        }
    }
}

@Composable
fun PharmacyContent(
    modifier: Modifier = Modifier,
    pharmacies: List<DataItem> = emptyList(),
) {

    Box(modifier = modifier) {
        LazyColumn {
            itemsIndexed(pharmacies) { index, pharmacy ->
                if (pharmacy != null) {
                    PharmacyCard(
                        name = pharmacy.nama ,
                        address = pharmacy.alamat,
                        imageUrl = pharmacy.foto ,
                        rating = pharmacy.rating.toString().toDoubleOrNull() ?: 0.0,
                        reviewCount = pharmacy.review.toDouble() ?: 0.0
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PharmacyScreenPreview() {
    PharmacyScreen()
}




