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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bpjs.mediconnet.component.PharmacyCard
import com.bpjs.mediconnet.component.ShimmerPharmacyItem
import com.bpjs.mediconnet.di.Injection
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.DataItem
import com.bpjs.mediconnet.viewmodel.PharmacyViewModel
import com.bpjs.mediconnet.viewmodel.ViewModelFactory


@Composable
fun PharmacyScreen(
    viewModel: PharmacyViewModel = viewModel(factory = ViewModelFactory.getInstance(Injection.provideRepository()))
) {


    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                LazyColumn {
                    items(10) { index ->
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
                Text(text = uiState.errorMessage ?: "Error")
            }

            else -> {}
        }
    }


}

@Composable
fun PharmacyContent(
    pharmacies: List<DataItem> = emptyList(),
    modifier: Modifier = Modifier,
) {

    Box(modifier = modifier) {
        LazyColumn {
            itemsIndexed(pharmacies) { index, pharmacy ->
                if (pharmacy != null) {
                    PharmacyCard(
                        name = pharmacy.nama ?: "",
                        address = pharmacy.alamat ?: "",
                        imageUrl = pharmacy.foto ?: "",
                        rating = pharmacy.rating?.toString()?.toDoubleOrNull() ?: 0.0,
                        reviewCount = pharmacy.review?.toDouble() ?: 0.0
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
    val viewModel = PharmacyViewModel(Injection.provideRepository())
    PharmacyScreen(viewModel = viewModel)
}




