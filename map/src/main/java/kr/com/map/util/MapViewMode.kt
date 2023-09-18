package kr.com.map.util

import android.content.Context
import net.daum.mf.map.api.MapView

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-13
 * Time: 오전 11:06
 */
enum class MapViewMode {
    DEFAULT, SEARCH_FOOD, SEARCH_CAFE, SEARCH_CONVENIENCE, SEARCH_FLOWER;

    val isDefault: Boolean
        get() = this == DEFAULT
    val isNotDefault: Boolean
        get() = this != DEFAULT
}
