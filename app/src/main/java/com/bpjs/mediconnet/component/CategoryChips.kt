package com.bpjs.mediconnet.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bpjs.mediconnet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChips(
    labelName: String,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    AssistChip(onClick = onClick, label = { Text(labelName) }, leadingIcon = {
        Icon(
            icon,
            contentDescription = stringResource(R.string.dummy_content_desc),
            modifier = modifier.size(AssistChipDefaults.IconSize)
        )
    })
}

@Preview(showBackground = true)
@Composable
fun CategoryChipsPreview() {
    CategoryChips(
        labelName = "Terdekat",
        onClick = {},
        icon = Icons.Default.Home
    )
}