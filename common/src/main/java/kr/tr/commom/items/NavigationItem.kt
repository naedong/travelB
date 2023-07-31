package kr.tr.commom.items


import kr.tr.commom.R

sealed class NavigationItem(var  route : String, var icon : Int, var title : String ) {
    object mainHome : NavigationItem("mainHome",  R.drawable.homelogo__,"Home")
    object home : NavigationItem("home",R.drawable.baseline_home_24, "Home")
    object plan : NavigationItem("plan", R.drawable.baseline_calendar_month_24, "계획")
    object mypage : NavigationItem("mypage", R.drawable.baseline_person_24, "정보")
    object location : NavigationItem("location", R.drawable.baseline_place_24, "위치")
    object region : NavigationItem("region", R.drawable.baseline_map_24, "지도")

companion object{
    val bottomNavigationItems = listOf(
        NavigationItem.mainHome     ,
        NavigationItem.home         ,
        NavigationItem.region       ,
        NavigationItem.location     ,
        NavigationItem.plan         ,
        NavigationItem.mypage
    )
}
}

