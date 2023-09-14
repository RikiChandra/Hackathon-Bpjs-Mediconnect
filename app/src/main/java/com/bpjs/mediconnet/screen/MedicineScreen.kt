package com.bpjs.mediconnet.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bpjs.mediconnet.component.MedicineCard
import com.bpjs.mediconnet.elements.HeaderScreen
import com.bpjs.mediconnet.ui.theme.MediconnetTheme

@Composable
fun MedicineScreen() {
    Column {
        HeaderScreen(query = "",
            onQueryChange = {},
            onClickChat = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        LazyRow {
            item {
                MedicineCard(name = "", description = "", imageUrl = "")
                MedicineCard(name = "", description = "", imageUrl = "")
            }
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