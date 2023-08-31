package kr.tr.commom.utill

import java.time.format.DateTimeFormatter

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-30
 * Time: 오후 12:36
 */

val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")
val DayTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

val HourFormatter = DateTimeFormatter.ofPattern("h a")
