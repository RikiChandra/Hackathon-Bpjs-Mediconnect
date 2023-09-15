package com.bpjs.mediconnet.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bpjs.mediconnet.component.MedicineCard
import com.bpjs.mediconnet.elements.HeaderScreen
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.Medicine
import com.bpjs.mediconnet.ui.theme.MediconnetTheme
import com.bpjs.mediconnet.viewmodel.MedicineViewModel

@Composable
fun MedicineScreen(
    viewModel: MedicineViewModel = hiltViewModel()
) {
    Column {
        HeaderScreen(
            query = "",
            onQueryChange = {},
            onClickChat = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        viewModel.dataMedicine.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState) {
                is UiState.Loading -> {
                    viewModel.getMedicine()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    MedicineContent(dataMedicine = uiState.data)
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
fun MedicineContent(
    dataMedicine: List<Medicine>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(dataMedicine, key = { it.id}) { medicine ->
            MedicineCard(
                name = medicine.nama,
                description = medicine.deskripsi,
                imageUrl = medicine.foto)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MedicineScreenPreview() {
    MediconnetTheme {
        MedicineScreen()
    }
}