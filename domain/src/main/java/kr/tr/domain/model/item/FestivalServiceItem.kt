package kr.tr.domain.model.item

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize
import kr.tr.domain.model.Header

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-28
 * Time: 오후 4:40
 */

data class FestivalService(
    val getFestivalKr : GetFestivalKr
)

data class GetFestivalKr(
    val header: Header,
    val item: List<GetFestivalKrItem>,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

@Parcelize
data class GetFestivalKrItem(
    val addr1 : String?,
    val addr2: String?,
    val cntctTel : String?,
    val gugunNm: String?,
    val homepageUrl: String?,
    val itemcntnts: String?,
    val lat: Double?,
    val lng: Double?,
    val mainImgNormal: String?,
    val mainImgThumb: String?,
    val mainPlace: String?,
    val mainTitle: String?,
    val middelSizeRm1: String?,
    val place: String?,
    val subtitle: String?,
    val title: String?,
    val trfcInfo: String?,
    val ucSeq: Int?,
    val usageAmount: String?,
    val usageDay: String?,
    val usageDayWeekAndTime: String?
) : Parcelable


@Immutable
@Parcelize
data class GetFestivalKrItemWrapper(var item: GetFestivalKrItem?) : Parcelable