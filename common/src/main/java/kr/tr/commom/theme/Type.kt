package kr.tr.commom.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kr.tr.commom.R
import kr.tr.commom.font.maruburi_bold
import kr.tr.commom.font.maruburi_extralight
import kr.tr.commom.font.maruburi_light
import kr.tr.commom.font.maruburi_regular
import kr.tr.commom.font.maruburi_semibold


val maruBuri_Bold = FontFamily(
    Font(R.font.maruburi_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.maruburi_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.maruburi_bold, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.maruburi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.maruburi_bold, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.maruburi_bold, FontWeight.Medium, FontStyle.Normal),
)

val maruBuri_ExtraLight = FontFamily(
    Font(R.font.maruburi_extralight, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.maruburi_extralight, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.maruburi_extralight, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.maruburi_extralight, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.maruburi_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.maruburi_extralight, FontWeight.Medium, FontStyle.Normal),

)

val maruBuri_Light = FontFamily(
    Font(R.font.maruburi_light, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.maruburi_light, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.maruburi_light, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.maruburi_light, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.maruburi_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.maruburi_light, FontWeight.Medium, FontStyle.Normal),
)

val maruBuri_Regular = FontFamily(
    Font(R.font.maruburi_regular, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.maruburi_regular, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.maruburi_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.maruburi_regular, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.maruburi_regular, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.maruburi_regular, FontWeight.Medium, FontStyle.Normal),
)

val maruBuri_SemiBold = FontFamily(
    Font(R.font.maruburi_semibold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.maruburi_semibold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.maruburi_semibold, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.maruburi_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.maruburi_semibold, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.maruburi_semibold, FontWeight.Medium, FontStyle.Normal),
)


val the_Jamsil_Oft_Light = FontFamily(
    Font(R.font.the_jamsil_otf_2_light, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.the_jamsil_otf_2_light, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.the_jamsil_otf_2_light, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.the_jamsil_otf_2_light, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.the_jamsil_otf_2_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.the_jamsil_otf_2_light, FontWeight.Medium, FontStyle.Normal),
)
val hakgyoanasimwooju = FontFamily(
    Font(R.font.hakgyoansimwoojur, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.hakgyoansimwoojur, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.hakgyoansimwoojur, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.hakgyoansimwoojur, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.hakgyoansimwoojur, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.hakgyoansimwoojur, FontWeight.Medium, FontStyle.Normal),

    )

class CusTypoClass(
    val maruBuri_Regular : FontFamily,
    val maruBuri_SemiBold : FontFamily,
    val maruBuri_Light : FontFamily,
    val maruBuri_ExtraLight : FontFamily,
    val maruBuri_Bold : FontFamily,
    val thejamsiloftlight : FontFamily,
    val hakgyoanasimwoojur : FontFamily,
)

fun customTypography(
    maruBuri_Regular : FontFamily = maruburi_regular,
    maruBuri_SemiBold : FontFamily = maruburi_semibold,
    maruBuri_Light : FontFamily = maruburi_light,
    maruBuri_ExtraLight : FontFamily = maruburi_extralight,
    maruBuri_Bold : FontFamily = maruburi_bold,
    theJamsilOftLight: FontFamily = the_Jamsil_Oft_Light,
    hakgyoanasimwoojur: FontFamily = hakgyoanasimwooju

) : CusTypoClass =
    CusTypoClass (
        maruBuri_Regular,
        maruBuri_SemiBold,
        maruBuri_Light,
        maruBuri_ExtraLight,
        maruBuri_Bold,
        hakgyoanasimwoojur,
        theJamsilOftLight
    )

val customTypographys = Typography(
    bodyMedium = TextStyle(
        fontFamily = maruBuri_Regular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = maruBuri_Regular,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = maruBuri_Regular,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)