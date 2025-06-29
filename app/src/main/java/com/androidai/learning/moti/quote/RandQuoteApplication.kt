package com.androidai.learning.moti.quote

import android.app.Application
import androidx.compose.ui.graphics.toArgb
import com.androidai.framework.theme.sandroid.ui.Mode
import com.androidai.framework.theme.sandroid.ui.SAndroidThemeCore
import com.androidai.framework.theme.sandroid.ui.compose.style.SAndroidUIColorCodes
import com.androidai.framework.theme.sandroid.ui.data.SAndroidUIColors
import com.androidai.framework.theme.sandroid.ui.data.model.DefaultThemeInfo
import com.sparrow.framework.core.avengerad.AvengerAdCore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class RandQuoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AvengerAdCore.initAvengerAdmobCore(this, BuildConfig.DEBUG, null, CoroutineScope(Dispatchers.IO))
        SAndroidThemeCore.init(
            this, DefaultThemeInfo(
                Mode.DARK.value, SAndroidUIColorCodes.ColorActionViolet.toArgb(), false))
    }
}