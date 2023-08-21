package kr.tr.commom.utill

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오전 11:59
 */
fun showLongToast(context : Context, message : String ){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
fun showShortToast(context : Context, message : String ){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
