package kr.com.map.item

import android.Manifest
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kr.com.map.presentation.main.setTrackingMode
import kr.com.map.presentation.model.MapViewModel
import net.daum.mf.map.api.MapView

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-13
 * Time: 오후 5:30
 */
@OptIn( ExperimentalPermissionsApi::class)
@Composable
fun permissionCheck(
    viewModel: MapViewModel,
    tracking: State<MapView.CurrentLocationTrackingMode>,
    permissionBool: MutableState<Boolean>
) {
    val permissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    if(permissionState.status.isGranted){
        viewModel.setTrackingMode(setTrackingMode(mode = tracking.value))
        permissionBool.value = false
    }
    else {
        AlertDialog(onDismissRequest = {
            permissionBool.value = false
        },
            containerColor = Color.White,
            dismissButton = {
                ElevatedButton(onClick = {
                    permissionBool.value = false
                },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(0.8f),
                        contentColor = Color.White
                    ),
                    enabled = true,
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 5.dp
                    )
                ) {
                    Text("닫 기")
                }

            },
            confirmButton = {
                ElevatedButton(onClick = {
                    permissionState.launchPermissionRequest()
                    permissionBool.value = false
                },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                    ),
                    enabled = true,
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 5.dp
                    )
                ) {
                    Text("권한 요청")
                }
            },
            title = {
                Text(text = "지도 권한 요청")
            },
            text = {
                Text(text = "지도 권한을 요청하실려면 권한 요청 버튼을 눌러주세요.")
            }
        )
    }
}
