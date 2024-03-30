package com.androidai.learning.moti.quote.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.androidai.learning.moti.quote.R
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotiQuoteScreen(viewModel: MotiQuoteViewModel) {
    val currentQuote = viewModel.currentQuote.observeAsState().value // Observe LiveData using collectAsState

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)) {

        val colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
        Surface(tonalElevation = 3.dp) {
            TopAppBar(modifier = Modifier.statusBarsPadding(), colors = colors, title = {
                Text(
                    text = stringResource(id = R.string.app_name), fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface)
            })
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = currentQuote?.text
                    ?: "", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onSurface,)

            if(currentQuote?.author != null) {
                Text(
                    text = "- ${currentQuote.author}", fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.onSurface)
            }
        }

    }
}

