package kr.tr.domain.model.item

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.paging.compose.LazyPagingItems
import kotlinx.android.parcel.Parcelize
import kr.tr.domain.model.Header


data class AttractionServiceItem(
    val getAttractionKr: GetAttractionKr
)

data class GetAttractionKr(
    val header: Header,
    val item: List<GetAttractionKrItem>,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)
@Parcelize
data class GetAttractionKrItem(
    val addr1: String,
    val cntctTel: String,
    val gugunNm: String,
    val hldyInfo: String,
    val homepageUrl: String,
    val itemcntnts: String,
    val lat: Double,
    val lng: Double,
    val mainImgNormal: String,
    val mainImgThumb: String,
    val mainTitle: String,
    val middelSizeRm1: String,
    val place: String,
    val subtitle: String,
    val title: String,
    val trfcInfo: String,
    val ucSeq: Int,
    val usageAmount: String,
    val usageDay: String,
    val usageDayWeekAndTime: String
) : Parcelable

@Immutable
@Parcelize
data class GetAttractionKrItemWrapper(var item: GetAttractionKrItem?) : Parcelable