package kr.com.map.data.model

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오전 12:23
 */
data class Address(
    val addressName: String,
    val region1depthName: String,
    val region2depthName: String,
    val region3depthName: String,
    val region3depthHName: String,
    val hCode: String,
    val bCode: String,
    val mountainYn: String,
    val mainAddressNo: String,
    val x: String,
    val y: String,
)
