package kr.tr.commom.utill

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오전 11:59
 */

fun Context.showToast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
fun Context.showLongToast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}


fun Context.showToast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}