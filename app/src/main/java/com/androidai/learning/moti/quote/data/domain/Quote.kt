package com.androidai.learning.moti.quote.data.domain

import android.text.SpannableString
import android.text.SpannableStringBuilder

data class Quote(val text: String, val author: String? = null, val category:String){

    fun getCopyShareContent() : String {
        val spannableString = SpannableStringBuilder(text)
        if(author != null){
            spannableString.append("\n")
            spannableString.append("   -$author")
        }

        return spannableString.toString()
    }
}
