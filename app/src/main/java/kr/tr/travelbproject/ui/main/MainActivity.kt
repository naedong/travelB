package kr.tr.travelbproject.ui.main

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint
import kr.tr.commom.theme.TravelBTheme
import java.security.MessageDigest
import kotlin.system.exitProcess

/**
 * TravelBAppProject
 * Created by Naedong
 * Date: 2023-06-13
 * Time: 오전 11:24
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            if(!isNetworkAvailable(this)){
                val toast = Toast.makeText(this, "인터넷 연결을 확인해주세요.  \r\n어플리케이션을 종료합니다.", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
                toast.show()

                ActivityCompat.finishAffinity(this)
                exitProcess(0)
            }

            TravelBTheme {
                    MainScreen()
            }
        }
    }

    fun isNetworkAvailable(context : Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false

            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}

