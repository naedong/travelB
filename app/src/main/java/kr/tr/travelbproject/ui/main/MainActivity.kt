package kr.tr.travelbproject.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kr.tr.commom.theme.TravelBTheme

/**
 * TravelBAppProject
 * Created by Naedong
 * Date: 2023-06-13
 * Time: 오전 11:24
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelBTheme {
                    MainScreen()
            }
        }
    }
}

