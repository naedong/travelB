package kr.com.map.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.tr.commom.R
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import net.daum.mf.map.api.MapView

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-10
 * Time: 오후 9:42
 */
@Composable
fun ColumnScope.MainFloatingActionButtons(
    trackingMode: CurrentLocationTrackingModel,
    onTrackingModeClick: () -> Unit,
    onListModeClick: () -> Unit
) {


    MainFloatingActionButton(
        onClick = onTrackingModeClick,
    ) {

        val res = when (trackingMode) {
            CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> R.drawable.baseline_gps_mode
            CurrentLocationTrackingModel.TrackingModeOnWithHeading -> R.drawable.baseline_comepass
            else -> R.drawable.baseline_gps_fixed
        }
        Image(painter = painterResource(id = res), contentDescription = "")

    }
}

@Composable
fun ColumnScope.MainFloatingActionButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .align(Alignment.End),
        shape = CircleShape,
        containerColor = Color.White,
        content = content
    )
}
