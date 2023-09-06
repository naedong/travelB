package kr.tr.home.item

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-04
 * Time: 오후 2:47
 */
@Composable
fun ShowMap(lat : Double, lng : Double, by : String = "FOOT") {
    val dataMap = KakaoMapURL()

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(KakaoMapURL().appPrefix + "${lat},${lng}"))
    intent.setPackage(KakaoMapURL().kakaoId)
    if(isAppInstalled(packageName = dataMap.kakaoId)){
        LocalContext.current.startActivity(intent)
    }
    else {
        Text(text = "설치되어 있는 앱이 없습니다")
    }



}

@Composable
fun isAppInstalled(packageName: String) : Boolean {
    val context = LocalContext.current
    return try {
        context.packageManager.getPackageInfoCompat(KakaoMapURL().kakaoId,)
        true
    } catch (e: Exception){ false }
}


fun PackageManager.getPackageInfoCompat(packageName: String, flags : Int = 0) : PackageInfo =
    if(VERSION.SDK_INT >= VERSION_CODES.TIRAMISU){
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    }
else {
    @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
}