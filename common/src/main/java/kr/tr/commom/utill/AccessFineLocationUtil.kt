//package kr.tr.commom.utill
//
//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.provider.Settings
//import androidx.appcompat.app.AlertDialog
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//
///**
// * TravelBProject
// * Created by Naedong
// * Date: 2023-08-09
// * Time: 오후 4:44
// */
//object AccessFineLocationUtil {
//    private const val PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE = 1000
//    fun requestPermission(activity: Activity) {
//        val permission = Manifest.permission.ACCESS_FINE_LOCATION
////        if (SharedPreferenceManager.isFirst(activity) || ActivityCompat.shouldShowRequestPermissionRationale(
////                activity,
////                permission
////            )
////        ) {
////            SharedPreferenceManager.setFirst(activity)
//            ActivityCompat.requestPermissions(
//                activity, arrayOf(permission),
//                PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE
//            )
//        }
//    }
//
//    fun checkPermission(
//        activity: Activity,
//        onGranted: () -> Unit,
//        onRequest: () -> Unit,
//        onCanceled: () -> Unit
//    ) {
//        val permission = Manifest.permission.ACCESS_FINE_LOCATION
//        if (ContextCompat.checkSelfPermission(
//                activity,
//                permission
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            onGranted()
//        } else if (SharedPreferenceManager.isFirst(activity) || ActivityCompat.shouldShowRequestPermissionRationale(
//                activity,
//                permission
//            )
//        ) {
//            SharedPreferenceManager.setFirst(activity)
//            ActivityCompat.requestPermissions(
//                activity, arrayOf(permission),
//                PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE
//            )
//        } else {
//            AlertDialog.Builder(activity)
//                .setMessage(R.string.AlertDialog_message_request_location_permission)
//                .setPositiveButton(R.string.AlertDialog_button_ok) { _, _ ->
//                    onRequest()
//                    val intent = Intent(
//                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                        Uri.parse("package:" + activity.packageName)
//                    )
//                    activity.startActivity(intent)
//                }.setNegativeButton(R.string.AlertDialog_button_cancel) { _, _ ->
//                    onCanceled()
//                }.setOnCancelListener {
//                    onCanceled()
//                }.show()
//        }
//    }
//
//    fun onRequestPermissionsResult(
//        requestCode: Int,
//        grantResults: IntArray,
//        onGranted: Runnable,
//        onDenied: Runnable
//    ) {
//        if (requestCode == PERMISSION_ACCESS_FINE_LOCATION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                onGranted.run()
//            } else {
//                onDenied.run()
//            }
//        }
//    }
//}