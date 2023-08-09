package kr.tr.commom.utill

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 3:52
 */
class PermissionCheck {


    @Composable
    fun GPSPermissionCheck() {
        var permissionGranted by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val preferences =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                permissionGranted = isGranted
            }

        if (!permissionGranted) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { preferences.launch(android.Manifest.permission.ACCESS_FINE_LOCATION) }) {
                    Text("Request GPS Permission")
                }
            }
        }

    }
}