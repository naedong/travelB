package kr.com.map.item

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-10
 * Time: 오후 9:42
 */

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

