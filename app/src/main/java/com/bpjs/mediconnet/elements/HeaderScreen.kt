package com.bpjs.mediconnet.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bpjs.mediconnet.R
import com.bpjs.mediconnet.component.SearchBar
import com.bpjs.mediconnet.ui.theme.MediconnetTheme

@Composable
fun HeaderScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onClickChat: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .background(Color(0xFF86C8BC))
            .padding(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
                Text(text = "Hello, Welcome👋", color = Color.White)
            }
            IconButton(onClick = onClickChat) {
                Icon(imageVector = Icons.Default.MailOutline, contentDescription = null, tint = Color(0xFFD6D6D6))
            }
        }
        SearchBar(
            query = query, onQueryChange = onQueryChange,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeaderScreenPreview() {
    MediconnetTheme {
        HeaderScreen(
            "",
            {},
            {}
        )
    }
}