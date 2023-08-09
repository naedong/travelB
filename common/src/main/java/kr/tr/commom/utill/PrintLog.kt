package kr.tr.commom.utill

import android.util.Log

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 2:17
 */
fun PrintLog(message : String) {
    Log.e(ClassLoader.getSystemClassLoader().javaClass.simpleName, "$message")
}