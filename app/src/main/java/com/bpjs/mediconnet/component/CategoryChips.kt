package com.bpjs.mediconnet.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChips(
    labelName: String,
    onClick: () -> Unit,
) {
    SuggestionChip(
        onClick = onClick, label = {
            Text(labelName)
        }
    )
}

