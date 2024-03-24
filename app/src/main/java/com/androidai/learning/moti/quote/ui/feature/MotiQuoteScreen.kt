package com.androidai.learning.moti.quote.ui.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModel

@Composable
fun MotiQuoteScreen(viewModel: MotiQuoteViewModel) {
    val currentQuote = viewModel.currentQuote.observeAsState().value // Observe LiveData using collectAsState

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(
            text = currentQuote?.text ?: "",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if (currentQuote?.author != null) {
            Text(
                text = "- ${currentQuote.author}",
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

