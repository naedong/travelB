//package kr.com.map.util
//
//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.provider.Settings
//import android.util.Log
//import androidx.activity.compose.ManagedActivityResultLauncher
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberUpdatedState
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.rememberPermissionState
//import com.google.accompanist.permissions.shouldShowRationale
//import kr.com.map.presentation.model.MapViewModel
//import kr.tr.domain.datasource.MapDataStore
//import kr.tr.domain.repository.local.MapDataStoreRepository
//import kr.tr.commom.R
//import kr.tr.commom.theme.CustomMaterialTheme
//
///**
// * TravelBProject
// * Created by Naedong
// * Date: 2023-08-11
// * Time: 오전 10:14
// */
//@Composable
//fun resultRegister(): ManagedActivityResultLauncher<Intent, ActivityResult> {
//    val startForResult =
//        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS
//            val data = it.data
//            Log.e(it.javaClass.simpleName, "$data")
//        }
//    return startForResult
//}
//
//
//object AccessFineLocationUtil {
//    private const val PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE = 1000
//
//
//    @OptIn(ExperimentalPermissionsApi::class)
//    @Composable
//    fun PermissionScreen(
//        activity: Activity,
//        viewModel: MapViewModel,
//        onPermissionGranted: () -> Unit,
//        onRequestPermission: () -> Unit,
//        onPermissionCanceled: () -> Unit,
//        resultRegister: ActivityResultLauncher<Intent>
//    ) {
//
//        val context = LocalContext.current
//        val permission = Manifest.permission.ACCESS_FINE_LOCATION
//
//        val permissionState = rememberPermissionState(permission = permission)
//        val showDialog = remember { mutableStateOf(false) } // 상태를 사용하여 AlertDialog 표시 여부 관리
//
//        // 상태 업데이트를 위해 rememberUpdatedState 사용
//        val permissionStateUpdated = rememberUpdatedState(permissionState)
//
//        LaunchedEffect(permissionState) {
//            if (ContextCompat.checkSelfPermission(
//                    activity, permission
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                onPermissionGranted()
//
//            }
//
//            else if (viewModel.getIsBool() || ActivityCompat.shouldShowRequestPermissionRationale(
//                    activity,
//                    permission
//                )
//            ) {
//                viewModel.boolLiveData.value == false
//                ActivityCompat.requestPermissions(
//                    activity, arrayOf(permission),
//                    PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE
//                )
//            } else {
//                showDialog.value = true
//            }
//        }
//        if(showDialog.value) {
//            AlertDialog(
//                onDismissRequest = { onPermissionCanceled() },
//                text = {
//                    Text(
//                        text = "${
//                            R.string
//                                .AlertDialog_message_request_location_permission
//                        }",
//                        fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
//                    )
//                },
//                confirmButton = {
//                    TextButton(onClick = {
//                        onRequestPermission()
//                        val intent = Intent(
//                            Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS,
//                            Uri.parse("package:" + activity.packageName)
//                        )
//                        resultRegister.launch(intent)
//                        showDialog.value = false
//                    }) {
//                        Text(
//                            text = stringResource(id = R.string.AlertDialog_button_ok),
//                            fontFamily = CustomMaterialTheme.typography.thejamsiloftlight
//                        )
//                    }
//                },
//                dismissButton = {
//                    TextButton(onClick = {
//                        onPermissionCanceled()
//                    }) {
//                        Text(
//                            text = "취소",
//                            fontFamily = CustomMaterialTheme.typography.thejamsiloftlight
//                        )
//                    }
//                },
//                title = {
//                    Text(
//                        text = "알림창",
//                        fontFamily = CustomMaterialTheme.typography.thejamsiloftlight
//                    )
//                }
//            )
//        }
//
//        DisposableEffect(permissionState) {
//            onDispose {
//                // Clean up any resources if needed
//            }
//        }
//
//    }
//}
