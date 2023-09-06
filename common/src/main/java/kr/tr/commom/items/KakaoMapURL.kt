package kr.tr.home.item

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-04
 * Time: 오후 2:49
 */
data class KakaoMapURL(
    val kakaoMapScheme : String = "kakaomap://",
    val appPrefix : String = "kakaomap://look?p=",
    val webPrefix : String = "https://map.kakao.com/link/map/marker,",
    val kakaoId : String = "net.daum.android.map"
)



object kakaoWord{
    val search = "search?"
    val route = "route?"
    val routeSp = "sp="
    val routeEp = "ep="
}

object kakaoby {
    val car = "&by=CAR"
    val foot = "&by=FOOT"
    val publictransit = "&by=PUBLICTRANSIT"
}