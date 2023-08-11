package kr.com.map.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오전 12:18
 */
@Entity
data class DocumentEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "place_name") val placeName: String,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "road_address_name") val roadAddressName: String,
    @ColumnInfo(name = "x") val x: String,
    @ColumnInfo(name = "y") val y: String,
    @ColumnInfo(name = "rate") val rate: Float
)
