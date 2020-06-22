package com.nguyen.basemvvm.utils.extensions

import android.content.Context
import android.text.TextUtils
import com.nguyen.basemvvm.R
import com.nguyen.basemvvm.utils.Constant
import com.nguyen.basemvvm.utils.PATTERN_EMAIL_REGEX
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String?.validatePassword(context: Context): String {
    if(TextUtils.isEmpty(this)) {
        return context.getString(R.string.warning_pass_empty)
    } else if(this!!.length < 8) {
        return context.getString(R.string.warning_pass_format)
    } else {
        return ""
    }
}

fun String?.validateEmail(context: Context) : String {
    if(TextUtils.isEmpty(this)) {
        return context.getString(R.string.warning_email_empty)
    } else if(!this!!.emailValidator()) {
        return context.getString(R.string.warning_email_format)
    } else {
        return ""
    }
}

fun String?.emailValidator(): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val EMAIL_PATTERN = PATTERN_EMAIL_REGEX
    pattern = Pattern.compile(EMAIL_PATTERN.toString())
    matcher = pattern.matcher(this)
    return matcher.matches()
}