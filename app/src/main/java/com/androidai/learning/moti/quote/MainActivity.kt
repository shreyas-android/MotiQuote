package com.androidai.learning.moti.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.androidai.learning.moti.quote.repository.remote.RemoteQuoteRepositoryImpl
import com.androidai.learning.moti.quote.ui.feature.MotiQuoteScreen
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModel
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModelFactory
import com.androidai.learning.moti.quote.ui.theme.MotiQuoteTheme

class MainActivity : ComponentActivity() {

    private val motiQuoteViewModelFactory = MotiQuoteViewModelFactory(RemoteQuoteRepositoryImpl())

    private lateinit var viewModel : MotiQuoteViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, factory = motiQuoteViewModelFactory)[MotiQuoteViewModel::class.java]
        setContent {
            MotiQuoteTheme { // A surface container using the 'background' color from the theme
                // Replace with your ViewModel instance creation
                setContent {
                    MotiQuoteScreen(viewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchQuote()
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