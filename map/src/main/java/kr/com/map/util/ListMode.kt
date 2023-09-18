package kr.com.map.util

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-13
 * Time: 오전 11:07
 */
enum class ListMode {
    LIST, MAP;

    val isList: Boolean
        get() = this == LIST
}
