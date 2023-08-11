package kr.com.map.data.model

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오전 12:23
 */
data class RoadAddress(
    val addressName: String,
    val region1depthName: String,
    val region2depthName: String,
    val region3depthName: String,
    val roadName: String,
    val undergroundYn: String,
    val mainBuildingNo: String,
    val subBuildingNo: String,
    val buildingName: String,
    val zoneNo: String,
    val x: String,
    val y: String,
)
