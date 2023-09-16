package com.bpjs.mediconnet.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.bpjs.mediconnet.elements.HeaderScreen
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.DataItem
import com.bpjs.mediconnet.viewmodel.PharmacyViewModel


@Composable
fun PharmacyScreen(
    viewModel: PharmacyViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    Column {
        HeaderScreen(
            query = "",
            onQueryChange = {},
            onClickChat = {},
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp)
        )

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
                    PharmacyContent(pharmacies = uiState.data, navigateToDetail = navigateToDetail)

                }
                is UiState.Error -> {
                    Text(text = uiState.errorMessage)
                }

                else -> {}

            }
        }
    }
}

@Composable
fun PharmacyContent(
    modifier: Modifier = Modifier,
    pharmacies: List<DataItem> = emptyList(),
    navigateToDetail: (String) -> Unit
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
                        reviewCount = pharmacy.review.toDouble() ?: 0.0,
                        modifier = Modifier.clickable {
                            navigateToDetail(pharmacy.id)
                        }
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
    PharmacyScreen(
        navigateToDetail = {}
    )
}




