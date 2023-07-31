package kr.tr.commom.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CustomColorScheme (
        val mySchemePrimary : Color,
        val mySchemeSecondary : Color,
        val mySchemeOnPrimary: Color
)


fun customLightColorScheme(
    mySchemePrimary: Color = customColorLightToken.mainPrimary,
    mySchemeSecondary : Color = customColorLightToken.mainPurpleColor,
    mySchemeOnPrimary : Color = customColorLightToken.mainPinkColor
) : CustomColorScheme =
    CustomColorScheme (
        mySchemePrimary,
        mySchemeSecondary,
        mySchemeOnPrimary,
    )

fun customDarkColorScheme(
    mySchemePrimary: Color = customColorDarkToken.mainPrimary,
    mySchemeSecondary: Color = customColorDarkToken.mainPurpleColor,
    mySchemeOnPrimary : Color = customColorDarkToken.mainPinkColor
) : CustomColorScheme =
    CustomColorScheme (
        mySchemePrimary,
        mySchemeSecondary,
        mySchemeOnPrimary,
    )


object CustomMaterialTheme {
    val colorScheme: CustomColorScheme
        @Composable
        @ReadOnlyComposable
        get() = CustomLocalColorScheme.current

    val typography: CusTypoClass
        @Composable
        @ReadOnlyComposable
        get() = CustomLocalTypography.current
}

internal val CustomLocalColorScheme = staticCompositionLocalOf { customLightColorScheme() }
internal val CustomLocalTypography = staticCompositionLocalOf { customTypography() }

