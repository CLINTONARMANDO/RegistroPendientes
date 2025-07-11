package com.clindevstu.registropendientes.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MoradoPrimario,
    onPrimary = Blanco,
    primaryContainer = MoradoOscuro,
    onPrimaryContainer = Blanco,

    secondary = VerdeOscuro,
    onSecondary = Blanco,
    secondaryContainer = VerdeMusgo,
    onSecondaryContainer = Blanco,

    tertiary = PurpuraReal,
    onTertiary = Blanco,
    tertiaryContainer = Lila,
    onTertiaryContainer = Negro,

    background = Color(0xFF121212),
    onBackground = Blanco,

    surface = Color(0xFF1E1E1E),
    onSurface = Blanco,

    surfaceVariant = VerdeOliva,
    onSurfaceVariant = Blanco
)

private val LightColorScheme = lightColorScheme(
    primary = VerdePrimario,
    onPrimary = Blanco,
    primaryContainer = VerdeClaro,
    onPrimaryContainer = Negro,

    secondary = MoradoClaro,
    onSecondary = Blanco,
    secondaryContainer = Lavanda,
    onSecondaryContainer = Negro,

    tertiary = VerdeMenta,
    onTertiary = Negro,
    tertiaryContainer = VerdePastel,
    onTertiaryContainer = Negro,

    background = GrisFondo,
    onBackground = Negro,

    surface = Blanco,
    onSurface = Negro,

    surfaceVariant = Lila,
    onSurfaceVariant = Negro
)


@Composable
fun RegistroPendientesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}