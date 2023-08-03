package kr.tr.commom.utill

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-02
 * Time: 오후 2:57
 */
// <여기서 부터 > 까지 제거
fun DataPreprocessing(string : String ) : String = string.replace(Regex("<.*?>"), "")

fun DayPreProcessing(string : String) : Boolean = string.matches(Regex("[^0-7]"))