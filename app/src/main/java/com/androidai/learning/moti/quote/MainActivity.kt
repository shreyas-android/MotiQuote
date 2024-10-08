package com.androidai.learning.moti.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.androidai.learning.moti.quote.repository.remote.RemoteQuoteRepositoryImpl
import com.androidai.learning.moti.quote.ui.feature.MotiQuoteScreen
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModel
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModelFactory
import com.androidai.learning.moti.quote.ui.theme.MotiQuoteTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val motiQuoteViewModelFactory = MotiQuoteViewModelFactory(
        RemoteQuoteRepositoryImpl(), BuildConfig.QUOTES_API_KEY)

    private lateinit var viewModel : MotiQuoteViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = ViewModelProvider(
            this, factory = motiQuoteViewModelFactory)[MotiQuoteViewModel::class.java]
        setContent {
            MotiQuoteTheme { // A surface container using the 'background' color from the theme
                // Replace with your ViewModel instance creation
                    MotiQuoteScreen(viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.fetchQuote()
        }
    }
}

@Composable
fun Greeting(name : String, modifier : Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MotiQuoteTheme {
        Greeting("Android")
    }
}