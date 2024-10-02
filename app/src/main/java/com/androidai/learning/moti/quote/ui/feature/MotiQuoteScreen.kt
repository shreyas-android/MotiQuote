package com.androidai.learning.moti.quote.ui.feature

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import com.androidai.learning.moti.quote.R
import com.androidai.learning.moti.quote.ui.feature.viewmodel.MotiQuoteViewModel
import com.androidai.learning.moti.quote.utils.ContentUtils
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MotiQuoteScreen(viewModel : MotiQuoteViewModel) {

    val context = LocalContext.current
    val currentQuote =
        viewModel.currentQuote.observeAsState().value // Observe LiveData using collectAsState

    val isRefreshing = viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing.value, onRefresh = {
        viewModel.refresh()
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
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
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()) {
            val (indicator, quoteContainer, iconContainer) = createRefs()
            PullRefreshIndicator(isRefreshing.value, pullRefreshState,
                Modifier.constrainAs(indicator) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, scale = true, contentColor = MaterialTheme.colorScheme.primary)

            Column(modifier = Modifier.constrainAs(quoteContainer){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {

                if(!currentQuote?.category.isNullOrEmpty()) {
                    Text(
                        text = currentQuote?.category!!.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                        fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Text(
                    text = currentQuote?.text
                        ?: "",
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                )

                if(currentQuote?.author != null) {
                    Text(text = "- ${currentQuote.author}", fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }

            if(!currentQuote?.text.isNullOrEmpty()) {
                Row(modifier = Modifier
                    .padding(end = 16.dp, top = 16.dp)
                    .constrainAs(iconContainer) {
                        end.linkTo(parent.end)
                        top.linkTo(quoteContainer.bottom)

                    }) {

                    Icon(
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp)
                            .size(24.dp)

                            .clickable {
                                ContentUtils.shareContent(
                                    context = context, data = currentQuote?.getCopyShareContent()
                                        ?: "")
                            }, painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "", tint = MaterialTheme.colorScheme.primary)

                    Icon(
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp)
                            .size(24.dp)

                            .clickable {

                                ContentUtils.copyAndShowToast(
                                    context = context, result = currentQuote?.getCopyShareContent()
                                        ?: "")
                            }, painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = "", tint = MaterialTheme.colorScheme.primary)
                }
            }


        }

    }
}

