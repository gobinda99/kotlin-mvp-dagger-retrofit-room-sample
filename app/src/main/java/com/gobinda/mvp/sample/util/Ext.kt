package com.gobinda.mvp.sample.util

import android.graphics.Typeface
import android.view.Gravity
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


/**
 * Functional extension of View to show Snack Bar
 */
fun View.showSnackBar(message: String, duration: Int) {

    Snackbar.make(this, message, duration).show()
}

/**
 * Functional extension TextView to convert FontAwesome text to icon
 * using stringResId
 */
fun TextView.toIcon(stringResId: Int) {
    toIcon(context.getString(stringResId))
}

/**
 * Functional extension TextView to convert FontAwesome text to icon
 */
fun TextView.toIcon(text: CharSequence? = this.text) {
    gravity = Gravity.CENTER
    typeface = Typeface.createFromAsset(context.assets,
        "fonts/FontAwesome.ttf")
    this.text = text
}

/**
 * Functional extension String date sample 05/07/1988 to Sun 07 May, 1988
 */
fun String.toTargetDateFormat() : String {
    return try { val originalDate = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
        val targetDate  = SimpleDateFormat("c dd MMM, yyyy", Locale.getDefault())
        val date : Date = originalDate.parse(this)
        val target : String? = targetDate.format(date)
        return target?:this}catch (e: Throwable){this}
}

